package org.example.standalone;

import org.example.standalone.repository.ClusterVmSQLDAO;
import org.example.standalone.service.ClusterVmWebService;

import javax.xml.ws.Endpoint;

public class App {

    public static void main(String[] args) {
        ClusterVmSQLDAO dao = new ClusterVmSQLDAO();

        String url = "http://0.0.0.0:9000/ClusterVmService";
        Endpoint.publish(url, new ClusterVmWebService(dao));
    }
}
