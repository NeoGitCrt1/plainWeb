package p.ysy.db;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnHolder {
    public static final org.h2.jdbcx.JdbcConnectionPool p =
            JdbcConnectionPool.create("jdbc:h2:./test","u","u");
    static {
        p.setMaxConnections(4);
    }

    public static Connection conn(){
        try {
            Connection conn = p.getConnection();
            conn.setAutoCommit(true);
            return conn;

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

}
