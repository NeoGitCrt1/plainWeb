package p.ysy.controller;


import p.ysy.service.proxy.MyProxy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MahaloServlet extends BaseController implements IController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MahaloServlet.class);

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String d = req.getParameter("d");
        log.info("{}",req.getParameterMap());
        PrintWriter writer = resp.getWriter();
        writer.write(MyProxy.helloService.aloha(d));
        writer.close();
    }

    @Override
    public String path() {
        return "/myservlet";
    }
}