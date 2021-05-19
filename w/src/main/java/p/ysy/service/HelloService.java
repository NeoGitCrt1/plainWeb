package p.ysy.service;

public class HelloService implements IHelloService {
    public static final HelloService INS = new HelloService();

    @Override
    public String hi(String name) {
        return "hi " + name;
    }

    @Override
    public String aloha(String name) {
        return "mahalo " + name;
    }
}
