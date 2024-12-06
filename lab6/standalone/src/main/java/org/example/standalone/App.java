package org.example.standalone;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.example.standalone.exceptions.*;
import org.example.standalone.repository.ClusterVmSQLDAO;
import org.example.standalone.service.ClusterVmWebService;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.URI;

public class App {

    private static final URI BASE_URI =
            URI.create("http://localhost:8080/v1/");

    public static void main(String[] args) {
        HttpServer server = null;
        try {
            ResourceConfig resourceConfig = new ClassNamesResourceConfig(
                    ClusterVmWebService.class,
                    DuplicateErrorProvider.class,
                    NotFoundErrorProvider.class,
                    ValidationErrorProvider.class,
                    ForbiddenErrorProvider.class);

            server = GrizzlyServerFactory.createHttpServer(BASE_URI, resourceConfig);
            server.start();
            System.in.read();
            stopServer(server);
        } catch (IOException e) {
            e.printStackTrace();
            stopServer(server);
        }
    }

    private static void stopServer(HttpServer server) {
        if (server != null)
            server.stop();
    }
}
