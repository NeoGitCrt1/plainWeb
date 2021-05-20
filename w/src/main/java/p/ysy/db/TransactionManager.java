package p.ysy.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;

public class TransactionManager {
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);
    /**
     *
     * @param r logic in transaction
     * @param a after commit hook
     * @param e rollback for
     */
    public static void doTransaction(Consumer<Connection> r, Runnable a, Class<? extends Exception> e) {
        Connection c  = ConnHolder.conn();
        try {
            c.setAutoCommit(false);
            r.accept(c);
            c.commit();
            if (a != null) {
                executor.submit(a);
            }
        } catch (Exception e1) {
            if (e1.getClass().equals(e)) {
                try {
                    c.rollback();
                } catch (SQLException throwables) {
                    throw new RuntimeException(throwables);
                }
            }
            if (e1 instanceof RuntimeException) {
                throw (RuntimeException)e1;
            } else {
                throw new RuntimeException(e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException throwables) {
                throw new RuntimeException(throwables);
            }
        }
    }


}
