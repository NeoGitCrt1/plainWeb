package p.ysy.service.proxy;

import p.ysy.controller.MessageServlet;
import p.ysy.service.HelloService;
import p.ysy.service.IHelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MyProxy.class);

    public static IHelloService helloService = m();
    private static IHelloService m() {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("hi")) {
                    log.info("proxy hi p1");
                    return HelloService.INS.hi((String) args[0]);
                }
                return method.invoke(HelloService.INS, args);
            }
        };

        IHelloService hello = (IHelloService) Proxy.newProxyInstance(
                IHelloService.class.getClassLoader(), // 传入ClassLoader
                new Class[] { IHelloService.class }, // 传入要实现的接口
                handler); // 传入处理调用方法的InvocationHandler
        return hello;
    }
}
