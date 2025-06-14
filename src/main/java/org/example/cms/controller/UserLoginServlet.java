package org.example.cms.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.cms.model.UserModel;
import org.example.cms.dto.UserDTO;

import java.io.IOException;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String userRole = req.getParameter("user");

        ServletContext servletContext = getServletContext();
        UserDTO user = UserModel.findUser(servletContext, new UserDTO(name, password,userRole));

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else if (user.getUserRole().equals("admin")) {
            resp.sendRedirect(req.getContextPath()+"/Admin.jsp"+"?id="+user.getId());
        } else if (user.getUserRole().equals("employee")) {
            resp.sendRedirect(req.getContextPath()+"/Employee.jsp"+"?id="+user.getId());
        }
    }
}
