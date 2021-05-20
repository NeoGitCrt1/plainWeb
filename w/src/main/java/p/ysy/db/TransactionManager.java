package p.ysy.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

public class TransactionManager {
    /**
     *
     * @param r logic in transaction
     * @param e rollback for
     */
    public static void doTransaction(Consumer<Connection> r, Class<? extends Exception> e) {
        Connection c  = ConnHolder.conn();
        try {
            c.setAutoCommit(false);
            r.accept(c);
            c.commit();
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
