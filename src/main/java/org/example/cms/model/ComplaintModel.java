package org.example.cms.model;

import jakarta.servlet.ServletContext;
import org.apache.commons.dbcp2.BasicDataSource;
import org.example.cms.dto.ComplaintDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static ComplaintDTO findById(ServletContext servletContext, String id) {
        System.out.println(id);
        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("ds");
        try {
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM complaints WHERE complaint_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new ComplaintDTO(resultSet.getInt("complaint_id"),resultSet.getInt("employee_id"), resultSet.getString("description"), resultSet.getString("date_submitted"),resultSet.getString("status"),resultSet.getString("comment"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean updateComplaint(ServletContext servletContext, ComplaintDTO complaintDTO) {
        System.out.println(complaintDTO.toString());

        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("ds");
        try {
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE complaints SET description=? , date_submitted=? WHERE complaint_id = ?");
            preparedStatement.setString(1,complaintDTO.getDescription());
            preparedStatement.setString(2, complaintDTO.getDate());
            preparedStatement.setInt(3,complaintDTO.getId());
            int i = preparedStatement.executeUpdate();

            if (i>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public static boolean deleteComplaint(ServletContext servletContext, int id) {
        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("ds");
        try {
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM complaints WHERE complaint_id = ?");
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();

            if (i>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean adminComplaint(ServletContext servletContext, ComplaintDTO complaintDTO) {
        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("ds");
        try {
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE complaints SET description=? , date_submitted=?, comment = ?, status=? WHERE complaint_id = ?");
            preparedStatement.setString(1,complaintDTO.getDescription());
            preparedStatement.setString(2, complaintDTO.getDate());
            preparedStatement.setString(3,complaintDTO.getComment());
            preparedStatement.setString(4,complaintDTO.getStatus());
            preparedStatement.setInt(5,complaintDTO.getId());
            int i = preparedStatement.executeUpdate();

            if (i>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public List<ComplaintDTO> getById(ServletContext servletContext, String id) {
        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("ds");
        try {
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM complaints WHERE employee_id=? AND status='New'");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ComplaintDTO> complaintDTOS = new ArrayList<>();
            while (resultSet.next()) {
                complaintDTOS.add(new ComplaintDTO(
                        resultSet.getInt("complaint_id"),
                        resultSet.getInt("employee_id"),
                        resultSet.getString("description"),
                        resultSet.getString("date_submitted"),
                        resultSet.getString("status"),
                        resultSet.getString("comment")
                        ));
            }
            return complaintDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ComplaintDTO> getAllComplaints(ServletContext servletContext) {
        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("ds");
        try {
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM complaints");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ComplaintDTO> complaintDTOS = new ArrayList<>();
            while (resultSet.next()){
                complaintDTOS.add(new ComplaintDTO(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
            }
            return complaintDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
