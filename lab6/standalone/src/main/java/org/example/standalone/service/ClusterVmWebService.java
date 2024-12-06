package org.example.standalone.service;

import lombok.var;
import org.example.standalone.dto.Filter;
import org.example.standalone.exceptions.DuplicateError;
import org.example.standalone.exceptions.ForbiddenError;
import org.example.standalone.exceptions.NotFoundError;
import org.example.standalone.exceptions.ValidationError;
import org.example.standalone.models.ClusterVm;
import org.example.standalone.repository.ClusterVmSQLDAO;
import org.example.standalone.util.throttling.Throttler;
import org.example.standalone.util.throttling.ThrottlingException;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@RequestScoped
@Path("/vms")
@Produces({MediaType.APPLICATION_JSON})
public class ClusterVmWebService {

    private final ClusterVmSQLDAO clusterVmSQLDAO;
    private final Throttler throttler;

    public ClusterVmWebService() {
        this.clusterVmSQLDAO = new ClusterVmSQLDAO();
        this.throttler = Throttler.getInstance();
    }

    @GET
    public List<ClusterVm> getClusterVms(
            @QueryParam(value = "id") UUID id,
            @QueryParam(value = "vmid") Long vmid,
            @QueryParam(value = "name") String name,
            @QueryParam(value = "image") String image,
            @QueryParam(value = "cpu") Integer cpu,
            @QueryParam(value = "memory") Integer memory
    ) throws ThrottlingException {
        throttler.throttle();
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
    public String deleteClusterVm(
            @HeaderParam("Authorization") String auth,
            @PathParam(value = "id") UUID id) throws ValidationError, NotFoundError, ForbiddenError, ThrottlingException {
        throttler.throttle();
        checkUser(auth);

        var idV = Optional.ofNullable(id).orElseThrow(() -> new ValidationError("id"));
        if (!this.clusterVmSQLDAO.getClusterVmByID(idV).isPresent()){
            throw  new NotFoundError("id", id.toString());
        }

        return this.clusterVmSQLDAO.deleteClusterVm(id).toString();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public String createClusterVm( @HeaderParam("Authorization") String auth, ClusterVm clusterVm) throws ValidationError, DuplicateError, ForbiddenError, ThrottlingException {
        throttler.throttle();
        checkUser(auth);
        var vmidV = Optional.ofNullable(clusterVm.getVmid()).filter(v -> v > 0).orElseThrow(() -> new ValidationError("vmid"));
        var nameV = Optional.ofNullable(clusterVm.getName()).filter(s -> !s.trim().isEmpty()).orElseThrow(() -> new ValidationError("name"));
        var imageV = Optional.ofNullable(clusterVm.getImage()).filter(s -> !s.trim().isEmpty()).orElseThrow(() -> new ValidationError("image"));
        var cpuV = Optional.ofNullable(clusterVm.getCpu()).filter(c -> c > 0).orElseThrow(() -> new ValidationError("cpu"));
        var memoryV = Optional.ofNullable(clusterVm.getMemory()).filter(m -> m > 0).orElseThrow(() -> new ValidationError("memory"));

        if (vmidV != null && this.clusterVmSQLDAO.getClusterVmByVmid(vmidV).isPresent()) {
            throw new DuplicateError("vmid");
        }

        UUID id = UUID.randomUUID();
        this.clusterVmSQLDAO.createClusterVm(ClusterVm.builder()
                .id(id)
                .vmid(vmidV)
                .name(nameV)
                .image(imageV)
                .cpu(cpuV)
                .memory(memoryV)
                .build());
        return id.toString();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public String updateClusterVm(@HeaderParam("Authorization") String auth, ClusterVm clusterVm) throws NotFoundError, ValidationError, DuplicateError, ForbiddenError, ThrottlingException {
        throttler.throttle();
        checkUser(auth);

        var idV = Optional.ofNullable(clusterVm.getId()).orElseThrow(() -> new ValidationError("id"));

        if (clusterVm.getName() == null && clusterVm.getImage()  == null && clusterVm.getCpu()  == null && clusterVm.getMemory()  == null && clusterVm.getVmid()  == null) {
            throw new ValidationError("nothing to update");
        }

        if (clusterVm.getVmid() != null) {
            clusterVm.setVmid(Optional.of(clusterVm.getVmid()).filter(v -> v > 0).orElseThrow(() -> new ValidationError("vmid")));
        }

        if (clusterVm.getName() != null) {
             clusterVm.setName(Optional.of(clusterVm.getName()).filter(n -> !n.trim().isEmpty()).orElseThrow(() -> new ValidationError("name")));
        }

        if (clusterVm.getImage() != null) {
            clusterVm.setImage( Optional.of(clusterVm.getImage() ).filter(n -> !n.trim().isEmpty()).orElseThrow(() -> new ValidationError("image")));
        }

        if (clusterVm.getCpu() != null) {
            clusterVm.setCpu(Optional.of(clusterVm.getCpu()).filter(c -> c > 0).orElseThrow(() -> new ValidationError("cpu")));
        }

        if (clusterVm.getMemory() != null) {
            clusterVm.setMemory( Optional.of(clusterVm.getMemory()).filter(m -> m > 0).orElseThrow(() -> new ValidationError("memory")));
        }

        if (!this.clusterVmSQLDAO.getClusterVmByID(idV).isPresent()){
            throw new NotFoundError("id", clusterVm.getId().toString());
        }

        if (clusterVm.getVmid() != null && this.clusterVmSQLDAO.getClusterVmByVmid(clusterVm.getVmid()).isPresent()) {
            throw new DuplicateError("vmid");
        }

        return this.clusterVmSQLDAO.updateClusterVm(clusterVm).toString();
    }

    private void checkUser(String auth) throws ForbiddenError {
        String[] credentials = new String(Base64.getDecoder().decode(auth)).split(":");
        if (credentials.length != 2) {
            throw new ForbiddenError();
        }

        String basicAuthUser = "admin";
        String basicAuthPassword = "password";
        if (!credentials[0].equals(basicAuthUser) || !credentials[1].equals(basicAuthPassword)) {
            throw new ForbiddenError();
        }
    }

}
