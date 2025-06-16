package org.example.cms.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.cms.dto.UserDTO;
import org.example.cms.model.UserModel;

import java.io.IOException;

@WebServlet("/signUp")
public class UserSignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            ServletContext servletContext = req.getServletContext();

            if (UserModel.createUser(new UserDTO(name, email, password, "employee"), servletContext)) {
                resp.sendRedirect(req.getContextPath() + "/Employee.jsp");
            }
        } catch (Exception e) {
            resp.sendRedirect("error.jsp");
        }

    }
}
