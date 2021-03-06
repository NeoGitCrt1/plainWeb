package p.ysy.container;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.InstanceHandle;
import p.ysy.controller.IController;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.util.ServiceLoader;

import static io.undertow.servlet.Servlets.*;

public class WebServer {
    public static final String MYAPP = "/myapp";
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WebServer.class);

    public static void serve() throws ServletException {
        DeploymentInfo servletBuilder = deployment()
                .setContextPath(MYAPP)
                .setDeploymentName("a")
                .setClassLoader(WebServer.class.getClassLoader());
//        .addServlets(
//                servlet("MyServlet", MessageServlet.class)
//                        .addInitParam("message", "MyServlet")
//                        .addMapping("/myservlet"),
//                servlet("mahalo", MahaloServlet.class)
//                        .addInitParam("message", "mahalo")
//                        .addMapping("/mahalo"));
        ServiceLoader<IController> cList = ServiceLoader.load(IController.class);
        cList.forEach( c -> {
            servletBuilder.addServlet(
                    servlet(c.getClass().getName(), c.getClass(), () -> new InstanceHandle<Servlet>(){
                        @Override
                        public IController getInstance() {
                            return c;
                        }
                        @Override
                        public void release() { }
                    }).addMapping(c.path())
            );
        });

        DeploymentManager manager = defaultContainer().addDeployment(servletBuilder);
        manager.deploy();

        HttpHandler servletHandler = manager.start();
        PathHandler path = Handlers
                .path()
                //.path(Handlers.redirect(MYAPP))
                .addPrefixPath(MYAPP, servletHandler);

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(path)
                .setServerOption(UndertowOptions.ENABLE_HTTP2, true)
                .build();
        server.start();

    }
}
