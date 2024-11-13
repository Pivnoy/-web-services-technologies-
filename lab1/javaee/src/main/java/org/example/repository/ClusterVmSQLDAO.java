package org.example.repository;

import org.example.models.ClusterVm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClusterVmSQLDAO {

    private Connection connection;

    public ClusterVmSQLDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ClusterVm> getClusterVms(String filterBlock) {
        List<ClusterVm> persons = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            System.out.println("select * from cluster_vm where " + filterBlock + " ;");
            String queryStr = "select * from cluster_vm ";
            if (filterBlock != null && !filterBlock.isEmpty()) {
                queryStr += " where " + filterBlock;
            }
            queryStr += " ;";
            ResultSet rs = stmt.executeQuery(queryStr);
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                Long vmid = rs.getLong("vmid");
                String name = rs.getString("name");
                String image = rs.getString("image");
                Integer cpu = rs.getInt("cpu");
                Integer memory = rs.getInt("memory");
                persons.add(ClusterVm.builder().
                        id(id).
                        vmid(vmid).
                        name(name).
                        image(image).
                        cpu(cpu).
                        memory(memory).
                        build());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return persons;
    }

}
