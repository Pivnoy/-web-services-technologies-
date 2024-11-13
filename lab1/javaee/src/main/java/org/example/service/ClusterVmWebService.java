package org.example.service;

import lombok.Data;
import org.example.dto.Filter;
import org.example.models.ClusterVm;
import org.example.repository.ClusterVmSQLDAO;

import javax.annotation.Resource;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.example.util.ConnectionUtil;

import java.sql.Connection;
import java.util.List;

@WebService(serviceName = "ClusterVmService")
public class ClusterVmWebService {

    @WebMethod(operationName = "getClusterVms")
    public List<ClusterVm> getClusterVms(List<Filter> filters) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < filters.size(); i++) {
            builder.append(filters.get(i).formFilterStr());
            if (i != filters.size() - 1) {
                builder.append(" and ");
            }
        }

        ClusterVmSQLDAO dao = new ClusterVmSQLDAO(getConnection());
        return dao.getClusterVms(builder.toString());
    }

    private Connection getConnection() {
        return ConnectionUtil.getConnection();
    }

}
