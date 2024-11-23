package org.example.standalone.repository;

import org.example.standalone.dto.Filter;
import org.example.standalone.dto.QueryStatus;
import org.example.standalone.models.ClusterVm;
import org.example.standalone.repository.util.Executor;
import org.example.standalone.util.ConnectionUtil;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClusterVmSQLDAO {

    private final Executor executor;

    public ClusterVmSQLDAO() {
        this.executor = new Executor();
    }

    public List<ClusterVm> getClusterVms(List<Filter> filters) {
       String query =  "select * from cluster_vm " +(
               filters.isEmpty() ? "" :
               "where " + filters.stream().
                       filter(Objects::nonNull).map(Filter::toString).collect(Collectors.joining(" and ")))  + ";";

       List<ClusterVm> books = null;
       try {
           books = executor.execQuery(query, result -> {
               List<ClusterVm> arr = new ArrayList<>();
                while (result.next()) {
                    arr.add(parseClusterVm(result));
                }
                return arr;
            });
       } catch (SQLException | IllegalArgumentException e) {
           e.printStackTrace();
       }
       return books;
    }

    public QueryStatus deleteClusterVm(UUID clusterVmId) {
        String query = "delete from cluster_vm where id = " + formQueryValue(clusterVmId) + ";";

        try {
            executor.execUpdate(query);
            return QueryStatus.OK;
        } catch (SQLException e) {
            e.printStackTrace();
            return QueryStatus.ERROR;
        }
    }

    public QueryStatus createClusterVm(ClusterVm clusterVm) {
        String query = "insert into cluster_vm(id, vmid, name, image, cpu, memory) values("
                + formQueryValue(clusterVm.getId()) + ","
                + formQueryValue(clusterVm.getVmid()) + ","
                +  formQueryValue(clusterVm.getName())+ ","
                +  formQueryValue(clusterVm.getImage())+ ","
                +  formQueryValue(clusterVm.getCpu())+ ","
                +  formQueryValue(clusterVm.getMemory()) + ");";

        try {
            executor.execUpdate(query);
            return QueryStatus.OK;
        } catch (SQLException e) {
            e.printStackTrace();
            return QueryStatus.ERROR;
        }
    }

    public QueryStatus updateClusterVm(ClusterVm clusterVm) {
        String query = "update cluster_vm set " +
                Stream.of(formUpdateQuery("vmid", clusterVm.getVmid())
                                ,formUpdateQuery("name", clusterVm.getName())
                                ,formUpdateQuery("image", clusterVm.getImage())
                                ,formUpdateQuery("cpu", clusterVm.getCpu())
                                ,formUpdateQuery("memory", clusterVm.getMemory()))
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining(","))
                + " where id = " + formQueryValue(clusterVm.getId()) + ";";
        try {
            executor.execUpdate(query);
            return QueryStatus.OK;
        } catch (SQLException e) {
            e.printStackTrace();
            return QueryStatus.ERROR;
        }
    }

    public Optional<ClusterVm> getClusterVmByID(UUID id) {
        Optional<ClusterVm> clusterVm = Optional.empty();
        String query = "select * from cluster_vm where id = " + formQueryValue(id) + " ;";
        System.out.println(query);
        try {
            clusterVm = Optional.ofNullable(executor.execQuery(
                    query,
                    result -> {
                        result.next();
                        return parseClusterVm(result);
                    }
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clusterVm;
    }

    public Optional<ClusterVm> getClusterVmByVmid(Long vmid) {
        Optional<ClusterVm> clusterVm = Optional.empty();
        try {
            clusterVm = Optional.ofNullable(executor.execQuery(
                    "select * from cluster_vm where vmid = " + vmid + ";",
                    result -> {
                        result.next();
                        return parseClusterVm(result);
                    }
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clusterVm;
    }

    private static <T> String formUpdateQuery(String name, T value) {
        return value == null ? null : name + " = " + formQueryValue(value);
    }

    private static <T> String formQueryValue(T value) {
        if (value instanceof String || value instanceof UUID) {
            return "'" + value + "'";
        }

        return value.toString();
    }

    private ClusterVm parseClusterVm(ResultSet result) throws SQLException {
       return ClusterVm.builder().
                id(UUID.fromString(result.getString("id"))).
                vmid(result.getLong("vmid")).
                name(result.getString("name")).
                image(result.getString("image")).
                cpu(result.getInt("cpu")).
                memory(result.getInt("memory")).
                build();
    }
}
