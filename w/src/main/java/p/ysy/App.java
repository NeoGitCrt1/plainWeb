package p.ysy;

import p.ysy.container.WebServer;

import javax.servlet.ServletException;
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
