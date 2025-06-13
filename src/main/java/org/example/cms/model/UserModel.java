package org.example.cms.model;

import jakarta.servlet.ServletContext;
import org.apache.commons.dbcp2.BasicDataSource;
import org.example.cms.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static UserDTO findUser(ServletContext servletContext, UserDTO userDTO) {

        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("ds");
        try {
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ? and role = ?");
            preparedStatement.setString(1,userDTO.getUserName());
            preparedStatement.setString(2,userDTO.getPassword());
            preparedStatement.setString(3,userDTO.getUserRole());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new UserDTO(
                        resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)
                );
            }
        } catch (SQLException e) {
              throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean createUser(UserDTO employee, ServletContext servletContext) {
        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("ds");

        try {
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into users(username, password, email, role) values (?,?,?,?)");
            preparedStatement.setString(1,employee.getUserName());
            preparedStatement.setString(2,employee.getPassword());
            preparedStatement.setString(3,employee.getEmail());
            preparedStatement.setString(4,employee.getUserRole());
            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
