package p.ysy;

import p.ysy.container.WebServer;

import javax.servlet.ServletException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ServletException {
        System.out.println("Hello World!");
        // TODO add config
        WebServer.serve();
    }
}
