<%-- 
    Document   : search
    Created on : Mar 2, 2025, 5:25:37 PM
    Author     : thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="dto.ProjectDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Projects</title>
    </head>
    <body>

        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="logout" />
            <input type="submit" value="Logout"/>
        </form>

        <h2>Search for Startup Projects</h2>


        <form action="MainController" method="GET">
            <input type="hidden" name="action" value="search" />
            <input type="text" name="searchTerm" placeholder="Enter project name..." required/>
            <input type="submit" value="Search"/>
        </form>
        <form action="MainController" method="GET">
            <input type="hidden" name="action" value="viewAll" />
            <input type="submit" value="View All Projects"/>
        </form>

        <hr/>

        <h3>Search Results:</h3>

        <%
            List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("Projects");
            if (projects != null && !projects.isEmpty()) {
        %>
        <table border="1">
            <tr>
                <th>Project ID</th>
                <th>Project Name</th>
                <th>Description</th>
                <th>Status</th>
                <th>Estimated Launch</th>
            </tr>
            <% for (ProjectDTO project : projects) {%>
            <tr>
                <td><%= project.getId()%></td>
                <td><%= project.getProjectName()%></td>
                <td><%= project.getDescription()%></td>
                <td><%= project.getStatus()%></td>
                <td><%= project.getEstimatedLaunch()%></td>
            </tr>

            <td>   
                <form action="updateProject.jsp" method="GET">
                    <input type="hidden" name="projectId" value="<%= project.getId()%>" />
                    <input type="submit" value="Edit"/>
                </form>
                <h2>Update Project</h2>

                <form action="MainController" method="POST">
                    <input type="hidden" name="action" value="updateProject"/>
                    <input type="hidden" name="projectId" value="<%= project.getId()%>"/>

                    <label>Project Name:</label>
                    <input type="text" name="projectName" value="<%= project.getProjectName()%>" required/><br/>

                    <label>Description:</label>
                    <textarea name="description" required><%= project.getDescription()%></textarea><br/>

                    <label>Status:</label>
                    <select name="status">
                        <option value="Ideation" <%= project.getStatus().equals("Ideation") ? "selected" : ""%>>Ideation</option>
                        <option value="Development" <%= project.getStatus().equals("Development") ? "selected" : ""%>>Development</option>
                        <option value="Launch" <%= project.getStatus().equals("Launch") ? "selected" : ""%>>Launch</option>
                        <option value="Scaling" <%= project.getStatus().equals("Scaling") ? "selected" : ""%>>Scaling</option>
                    </select><br/>

                    <label>Estimated Launch:</label>
                    <input type="date" name="estimatedLaunch" value="<%= project.getEstimatedLaunch()%>" required/><br/>

                    <input type="submit" value="Update"/>
                </form>


            </td> 
            <% } %>
        </table>
        <%
        } else {
        %>
        <p>No projects found.</p>
        <%
            }
        %>

    </body>
</html>

