package p.ysy.controller;


import p.ysy.container.WebServer;
import p.ysy.service.HelloService;
import p.ysy.service.proxy.MyProxy;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Stuart Douglas
 */
public class MessageServlet extends BaseController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MessageServlet.class);

    public static final String MESSAGE = "message";

    private String message;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        message = config.getInitParameter(MESSAGE);
        log.info(message);
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String d = req.getParameter("d");
        log.info("{}",req.getParameterMap());
        PrintWriter writer = resp.getWriter();
        writer.write(MyProxy.helloService.hi(d));
        writer.close();
    }
}