package org.example.cms.dto;

import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String userRole;

    public UserDTO(String name, String password, String userRole) {
        this.userName = name;
        this.password = password;
        this.userRole = userRole;
    }

    public UserDTO(String name, String email, String password, String userRole) {
        this.userName = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }
}
