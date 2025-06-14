package org.example.cms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.cms.dto.ComplaintDTO;
import org.example.cms.model.ComplaintModel;

import java.io.IOException;

@WebServlet("/update_complaint")
public class UpdateComplaintServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String description = req.getParameter("description");
        String date = req.getParameter("date");
        String eid = req.getParameter("eid");

        System.out.println(id+" "+description+" "+date+" "+eid);
        if (ComplaintModel.updateComplaint(req.getServletContext(), new ComplaintDTO(id, description, date))) {
            resp.sendRedirect(req.getContextPath() + "/Employee.jsp?id="+eid);
        }
    }
}
