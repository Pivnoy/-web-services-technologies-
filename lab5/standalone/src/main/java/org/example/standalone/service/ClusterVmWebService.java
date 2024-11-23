package org.example.standalone.service;

import lombok.AllArgsConstructor;
import org.example.standalone.dto.Filter;
import org.example.standalone.dto.QueryStatus;
import org.example.standalone.models.ClusterVm;
import org.example.standalone.repository.ClusterVmSQLDAO;

import javax.jws.WebParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/vms")
@Produces({MediaType.APPLICATION_JSON})
public class ClusterVmWebService {

    private final ClusterVmSQLDAO clusterVmSQLDAO;

    public ClusterVmWebService() {
        this.clusterVmSQLDAO = new ClusterVmSQLDAO();
    }

    @GET
    public List<ClusterVm> getClusterVms(
            @QueryParam(value = "id") UUID id,
            @QueryParam(value = "vmid") Long vmid,
            @QueryParam(value = "name") String name,
            @QueryParam(value = "image") String image,
            @QueryParam(value = "cpu") Integer cpu,
            @QueryParam(value = "memory") Integer memory
    ) {
        List<Filter> filters = new ArrayList<>();
        if (id != null) {
            filters.add(new Filter("id", id));
        }

        if (vmid != null) {
            filters.add(new Filter("vmid", vmid));
        }

        if (name != null) {
            filters.add(new Filter("name", name));
        }

        if (image != null) {
            filters.add(new Filter("image", image));
        }

        if (cpu != null) {
            filters.add(new Filter("cpu", cpu));
        }

        if (memory != null) {
            filters.add(new Filter("memory", memory));
        }

        return this.clusterVmSQLDAO.getClusterVms(filters);
    }

    @DELETE
    @Path("{id}")
    public String deleteClusterVm(@PathParam("id") UUID id) {
        return this.clusterVmSQLDAO.deleteClusterVm(id).toString();
    }

    @POST
    @Consumes("application/json")
    public String createClusterVm(ClusterVm clusterVm) {
        UUID id = UUID.randomUUID();
        clusterVm.setId(id);
        this.clusterVmSQLDAO.createClusterVm(clusterVm);
        return id.toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateClusterVm(ClusterVm clusterVm) {
        return this.clusterVmSQLDAO.updateClusterVm(clusterVm).toString();
    }
}
