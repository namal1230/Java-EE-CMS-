<%@ page import="org.example.cms.model.ComplaintModel" %>
<%@ page import="org.example.cms.dto.ComplaintDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/11/2025
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complaint Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link rel="stylesheet" href="style/employee.css">
</head>
<body>
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="NewComplaint.jsp?id=<%= request.getParameter("id")%>">New complaint</a>
        </li>
    </ul>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">employee_id</th>
            <th scope="col">description</th>
            <th scope="col">date_submitted</th>
            <th scope="col">status</th>
            <th scope="col">comment</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
    <%List<ComplaintDTO> complaintDTOS = new ComplaintModel().getById(request.getServletContext(),request.getParameter("id"));
    for (ComplaintDTO complaintDTO:complaintDTOS){
        %>
        <tr>
            <th scope="row"><%=complaintDTO.getId()%></th>
            <td><%=complaintDTO.getEmployee_id()%></td>
            <td><%=complaintDTO.getDescription()%></td>
            <td><%=complaintDTO.getDate()%></td>
            <td><%=complaintDTO.getStatus()%></td>
            <td><%=complaintDTO.getComment()%></td>
            <td>
                <a class="btn btn-info" href="UpdateComplaint.jsp?id=<%= complaintDTO.getId()%>">Update</a>
                <a class="btn btn-danger" href="DeleteComplaint.jsp?id=<%= complaintDTO.getId()%>">Delete</a>
            </td>
        </tr>

        <% } %>
        </tbody>
    </table>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
    <script src="js/checkToken.js"></script>
</body>
</html>
