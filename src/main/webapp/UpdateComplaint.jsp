<%@ page import="org.example.cms.model.ComplaintModel" %>
<%@ page import="org.example.cms.dto.ComplaintDTO" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/13/2025
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="style/new_complaint.css">
</head>
<body>
    <% ComplaintDTO complaint = ComplaintModel.findById(request.getServletContext(), request.getParameter("id"));%>
    <form class="row g-3" action="/update_complaint?id=<%= complaint.getId()%>&eid=<%= complaint.getEmployee_id()%>" method="post">
        <div class="col-md-6">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control" id="description" name="description" required value="<%=complaint.getDescription()%>">
        </div>
        <div class="col-md-6">
            <label for="date" class="form-label">Date</label>
            <input type="date" class="form-control" id="date" name="date" required value="<%=complaint.getDate()%>">
        </div>

        <div class="col-12">
            <button class="btn btn-primary" type="submit">Update</button>
        </div>
    </form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
    <script src="js/checkToken.js"></script>
</body>
</html>
