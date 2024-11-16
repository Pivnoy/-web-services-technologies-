package org.example.standalone.service;

import lombok.AllArgsConstructor;
import org.example.standalone.dto.Filter;
import org.example.standalone.dto.QueryStatus;
import org.example.standalone.models.ClusterVm;
import org.example.standalone.repository.ClusterVmSQLDAO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
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
            @XmlElement(required = true) @WebParam(name = "id") UUID id) {
        return this.clusterVmSQLDAO.deleteClusterVm(id);
    }

    @WebMethod(operationName = "createClusterVm")
    public UUID createClusterVm(
            @WebParam(name = "vmid") Long vmid,
            @WebParam(name = "name") String name,
            @WebParam(name = "image") String image,
            @WebParam(name = "cpu") Integer cpu,
            @WebParam(name = "memory") Integer memory
    ) {
        UUID id = UUID.randomUUID();
        this.clusterVmSQLDAO.createClusterVm(ClusterVm.builder()
                .id(id)
                .vmid(vmid)
                .name(name)
                        .image(image)
                        .cpu(cpu)
                        .memory(memory)
                .build());
        return id;
    }

    @WebMethod(operationName = "updateClusterVm")
    public QueryStatus updateClusterVm(
            @XmlElement(required = true) @WebParam(name = "id") UUID id,
            @WebParam(name = "vmid") Long vmid,
            @WebParam(name = "name") String name,
            @WebParam(name = "image") String image,
            @WebParam(name = "cpu") Integer cpu,
            @WebParam(name = "memory") Integer memory)
    {
        return this.clusterVmSQLDAO.updateClusterVm(ClusterVm.builder()
                .id(id)
                .vmid(vmid)
                .name(name)
                .image(image)
                .cpu(cpu)
                .memory(memory)
                .build());
    }
}
