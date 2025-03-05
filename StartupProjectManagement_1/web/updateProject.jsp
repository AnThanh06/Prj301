<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.ProjectDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Project</title>
    <style>
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input, select, textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Project</h2>
        <%
            ProjectDTO project = (ProjectDTO) request.getAttribute("project");
            if (project != null) {
        %>
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="updateProject"/>
            <input type="hidden" name="projectId" value="<%= project.getId() %>"/>
            
            <div class="form-group">
                <label>Project Name:</label>
                <input type="text" name="projectName" value="<%= project.getProjectName() %>" required/>
            </div>
            
            <div class="form-group">
                <label>Description:</label>
                <textarea name="description" required><%= project.getDescription() %></textarea>
            </div>
            
            <div class="form-group">
                <label>Status:</label>
                <select name="status">
                    <option value="Ideation" <%= project.getStatus().equals("Ideation") ? "selected" : "" %>>Ideation</option>
                    <option value="Development" <%= project.getStatus().equals("Development") ? "selected" : "" %>>Development</option>
                    <option value="Launch" <%= project.getStatus().equals("Launch") ? "selected" : "" %>>Launch</option>
                    <option value="Scaling" <%= project.getStatus().equals("Scaling") ? "selected" : "" %>>Scaling</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Estimated Launch:</label>
                <input type="date" name="estimatedLaunch" value="<%= project.getEstimatedLaunch() %>" required/>
            </div>
            
            <input type="submit" value="Update" class="btn"/>
        </form>
        <% } else { %>
            <p>Project not found.</p>
        <% } %>
    </div>
</body>
</html>