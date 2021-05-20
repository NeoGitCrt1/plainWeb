package p.ysy.service;

import p.ysy.db.TransactionManager;
import p.ysy.service.proxy.MyProxy;

import java.sql.SQLException;

public class HelloService implements IHelloService {
    public static final HelloService INS = new HelloService();
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MyProxy.class);

    @Override
    public String hi(String name) {
        return "hi " + name;
    }

    @Override
    public String aloha(String name) {
        TransactionManager.doTransaction(c -> {
            try {
                c.prepareStatement("select now()").executeQuery();
            } catch (SQLException throwables) {
                //
                log.error(">>", throwables);
            }
        }, () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                //
            }
            log.info("hook end");
        }, IllegalArgumentException.class);
        return "mahalo " + name;
    }
}
