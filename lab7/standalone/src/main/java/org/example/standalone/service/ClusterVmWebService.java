package org.example.standalone.service;

import lombok.AllArgsConstructor;
import org.example.standalone.dto.Filter;
import org.example.standalone.dto.QueryStatus;
import org.example.standalone.exceptions.DuplicateError;
import org.example.standalone.exceptions.NotFoundError;
import org.example.standalone.exceptions.ValidationError;
import org.example.standalone.exceptions.ValidationErrorFault;
import org.example.standalone.models.ClusterVm;
import org.example.standalone.repository.ClusterVmSQLDAO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebService(serviceName = "ClusterVmService")
@AllArgsConstructor
public class ClusterVmWebService {

    private ClusterVmSQLDAO clusterVmSQLDAO;

    @WebMethod(operationName = "getClusterVms")
    public List<ClusterVm> getClusterVms(List<Filter> filters) {
        return this.clusterVmSQLDAO.getClusterVms(filters);
    }

    @WebMethod(operationName = "deleteClusterVm")
    public QueryStatus deleteClusterVm(
            @WebParam(name = "id") UUID id) throws ValidationError, NotFoundError {
        var idV = Optional.ofNullable(id).orElseThrow(() -> new ValidationError("id"));
        if (this.clusterVmSQLDAO.getClusterVmByID(idV).isEmpty()){
            throw  new NotFoundError("id", id.toString());
        }

        return this.clusterVmSQLDAO.deleteClusterVm(id);
    }

    @WebMethod(operationName = "createClusterVm")
    public UUID createClusterVm(
            @WebParam(name = "vmid") Long vmid,
            @WebParam(name = "name") String name,
            @WebParam(name = "image") String image,
            @WebParam(name = "cpu") Integer cpu,
            @WebParam(name = "memory") Integer memory
    ) throws ValidationError, DuplicateError {
        var vmidV = Optional.ofNullable(vmid).filter(v -> v > 0).orElseThrow(() -> new ValidationError("vmid"));
        var nameV = Optional.ofNullable(name).filter(s -> !s.trim().isEmpty()).orElseThrow(() -> new ValidationError("name"));
        var imageV = Optional.ofNullable(image).filter(s -> !s.trim().isEmpty()).orElseThrow(() -> new ValidationError("image"));
        var cpuV = Optional.ofNullable(cpu).filter(c -> c > 0).orElseThrow(() -> new ValidationError("cpu"));
        var memoryV = Optional.ofNullable(memory).filter(m -> m > 0).orElseThrow(() -> new ValidationError("memory"));

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
        return id;
    }

    @WebMethod(operationName = "updateClusterVm")
    public QueryStatus updateClusterVm(
            @WebParam(name = "id") UUID id,
            @WebParam(name = "vmid") Long vmid,
            @WebParam(name = "name") String name,
            @WebParam(name = "image") String image,
            @WebParam(name = "cpu") Integer cpu,
            @WebParam(name = "memory") Integer memory) throws NotFoundError, ValidationError, DuplicateError {
        var idV = Optional.ofNullable(id).orElseThrow(() -> new ValidationError("id"));
        if (vmid != null) {
            vmid = Optional.of(vmid).filter(v -> v > 0).orElseThrow(() -> new ValidationError("vmid"));
        }

        if (name != null) {
             name = Optional.of(name).filter(n -> !n.trim().isEmpty()).orElseThrow(() -> new ValidationError("name"));
        }

        if (image != null) {
            image = Optional.of(image).filter(n -> !n.trim().isEmpty()).orElseThrow(() -> new ValidationError("image"));
        }

        if (cpu != null) {
            cpu = Optional.of(cpu).filter(c -> c > 0).orElseThrow(() -> new ValidationError("cpu"));
        }

        if (memory != null) {
            memory = Optional.of(memory).filter(m -> m > 0).orElseThrow(() -> new ValidationError("memory"));
        }

        if (this.clusterVmSQLDAO.getClusterVmByID(idV).isEmpty()){
            throw  new NotFoundError("id", id.toString());
        }

        if (vmid != null && this.clusterVmSQLDAO.getClusterVmByVmid(vmid).isPresent()) {
            throw new DuplicateError("vmid");
        }

        if (name == null && vmid == null && cpu == null && memory == null && image == null) {
            throw new ValidationError("nothing to update");
        }

        return this.clusterVmSQLDAO.updateClusterVm(ClusterVm.builder()
                .id(idV)
                .vmid(vmid)
                .name(name)
                .image(image)
                .cpu(cpu)
                .memory(memory)
                .build());
    }

}
