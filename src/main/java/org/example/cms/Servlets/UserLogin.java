package org.example.cms.Servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.cms.dao.UserDAO;
import org.example.cms.dto.UserDTO;

import java.io.IOException;

@WebServlet("/login/*")
public class UserLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String userRole = req.getParameter("user");
        ServletContext servletContext = getServletContext();
        UserDTO user = UserDAO.findUser(servletContext, new UserDTO(name, password,userRole));
        System.out.println(user);
        if (user == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else if (user.getUserRole().equals("admin")) {
            System.out.printf("AAAA");
            resp.sendRedirect("http://localhost:8089/Admin.jsp");
        } else if (user.getUserRole().equals("employee")) {
            resp.sendRedirect("http://localhost:8089/Employee.jsp");
        }
    }
}
