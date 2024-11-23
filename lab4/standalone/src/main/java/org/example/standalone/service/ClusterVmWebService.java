package org.example.standalone.service;

import org.example.standalone.models.ClusterVm;
import org.example.standalone.repository.ClusterVmSQLDAO;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vms")
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
