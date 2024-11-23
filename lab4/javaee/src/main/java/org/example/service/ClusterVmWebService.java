package org.example.service;

import org.example.models.ClusterVm;
import org.example.repository.ClusterVmSQLDAO;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import java.util.List;

@RequestScoped
@Path("vms")
@Produces({MediaType.APPLICATION_JSON})
public class ClusterVmWebService {

    private final ClusterVmSQLDAO clusterVmSQLDAO;

    public ClusterVmWebService() {
        this.clusterVmSQLDAO = new ClusterVmSQLDAO();
    }

    @GET
    public List<ClusterVm> getClusterVms(@QueryParam(value = "filters") List<String> filters) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < filters.size(); i++) {
            builder.append(filters.get(i));
            if (i != filters.size() - 1) {
                builder.append(" and ");
            }
        }

        return this.clusterVmSQLDAO.getClusterVms(builder.toString());
    }
}
