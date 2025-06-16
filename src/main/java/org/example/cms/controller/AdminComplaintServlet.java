package org.example.cms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.cms.dto.ComplaintDTO;
import org.example.cms.model.ComplaintModel;

import java.io.IOException;

@WebServlet("/admin_complaint")
public class AdminComplaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String description = req.getParameter("description");
        String date = req.getParameter("date");
        String eid = req.getParameter("eid");
        String comment = req.getParameter("comment");
        String status = req.getParameter("status");

        System.out.println(id+" "+description+" "+date+" "+eid);
        if (ComplaintModel.adminComplaint(req.getServletContext(), new ComplaintDTO(id, description, date, comment,status))) {
            resp.sendRedirect(req.getContextPath() + "/Admin.jsp");
        }
    }
}
