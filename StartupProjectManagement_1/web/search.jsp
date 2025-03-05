<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="dto.ProjectDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Projects</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .search-box {
            margin-bottom: 20px;
            display: flex;
            gap: 10px;
        }
        input[type="text"], input[type="submit"] {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .actions {
            white-space: nowrap;
        }
        .btn {
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 4px;
            color: blue;
        }
        .btn-edit {
            background-color: #2196F3;
        }
        .btn-logout {
            background-color: #f44336;
            float: right;
        }
        .message {
            color: green;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
            background-color: #e8f5e9;
        }
    </style>
</head>


<body><!-- Thêm vào phần trên cùng của search.jsp -->
    <a href="MainController?action=create" class="btn" style="margin-bottom: 20px; " color="red;">
        Create New Project
    </a>
    
    <div class="container">
        <% if (request.getSession().getAttribute("message") != null) { %>
            <div class="message">
                <%= request.getSession().getAttribute("message") %>
            </div>
            <% request.getSession().removeAttribute("message"); %>
        <% } %>
        
        <a href="MainController?action=logout" class="btn btn-logout">Logout</a>
        
        <h2>Search for Startup Projects</h2>
        
        <div class="search-box">
            <form action="MainController" method="GET" style="flex-grow: 1;">
                <input type="hidden" name="action" value="search" />
                <input type="text" name="searchTerm" placeholder="Enter project name..." 
                       value="<%= request.getParameter("searchTerm") != null ? request.getParameter("searchTerm") : "" %>"
                       style="width: 300px;"/>
                <input type="submit" value="Search"/>
            </form>
            <form action="MainController" method="GET">
                <input type="hidden" name="action" value="viewAll" />
                <input type="submit" value="View All Projects"/>
            </form>
        </div>

        <% List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("Projects"); %>
        <% if (projects != null && !projects.isEmpty()) { %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Project Name</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Launch Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (ProjectDTO project : projects) { %>
                    <tr>
                        <td><%= project.getId() %></td>
                        <td><%= project.getProjectName() %></td>
                        <td><%= project.getDescription() %></td>
                        <td><%= project.getStatus() %></td>
                        <td><%= project.getEstimatedLaunch() %></td>
                        <td class="actions">
                            <a href="MainController?action=edit&projectId=<%= project.getId() %>" 
                               class="btn btn-edit">Edit</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No projects found.</p>
        <% } %>
    </div>
</body>
</html>