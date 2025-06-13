package org.example.cms.model;

import jakarta.servlet.ServletContext;
import org.apache.commons.dbcp2.BasicDataSource;
import org.example.cms.dto.ComplaintDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComplaintModel {
    public static boolean saveComplaints(ServletContext servletContext, ComplaintDTO complaintDTO) {
        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("ds");

        try {
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO complaints (employee_id,description,date_submitted) VALUES (?,?,?)");
            preparedStatement.setInt(1, complaintDTO.getEmployee_id());
            preparedStatement.setString(2, complaintDTO.getDescription());
            preparedStatement.setString(3, complaintDTO.getDate());
            int i = preparedStatement.executeUpdate();

            if (i>0){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
