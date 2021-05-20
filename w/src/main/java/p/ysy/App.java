package p.ysy;

import org.h2.Driver;
import org.h2.jdbcx.JdbcConnectionPool;
import p.ysy.container.WebServer;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ServletException, SQLException {
        // TODO add config
        WebServer.serve();
    }
}
