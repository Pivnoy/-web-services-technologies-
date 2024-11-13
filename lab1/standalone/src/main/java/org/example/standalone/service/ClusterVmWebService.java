package org.example.standalone.service;

import lombok.AllArgsConstructor;
import org.example.standalone.dto.Filter;
import org.example.standalone.models.ClusterVm;
import org.example.standalone.repository.ClusterVmSQLDAO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "ClusterVmService")
@AllArgsConstructor
public class ClusterVmWebService {

    private ClusterVmSQLDAO ClusterVmSQLDAO;

    @WebMethod(operationName = "getClusterVms")
    public List<ClusterVm> getClusterVms(List<Filter> filters) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < filters.size(); i++) {
            builder.append(filters.get(i).formFilterStr());
            if (i != filters.size() - 1) {
                builder.append(" and ");
            }
        }

        return this.ClusterVmSQLDAO.getClusterVms(builder.toString());
    }
}
