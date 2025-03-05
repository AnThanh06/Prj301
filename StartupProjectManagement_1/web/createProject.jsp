<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create New Project</title>
    <style>
        .container { max-width: 600px; margin: 20px auto; padding: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, select, textarea { width: 100%; padding: 8px; border: 1px solid #ddd; }
        .btn { background: #4CAF50; color: white; padding: 10px 15px; border: none; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Create New Project</h2>
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="createProject"/>
            
            <div class="form-group">
                <label>Project Name:</label>
                <input type="text" name="projectName" required>
            </div>
            
            <div class="form-group">
                <label>Description:</label>
                <textarea name="description" rows="4" required></textarea>
            </div>
            
            <div class="form-group">
                <label>Status:</label>
                <select name="status" required>
                    <option value="Ideation">Ideation</option>
                    <option value="Development">Development</option>
                    <option value="Launch">Launch</option>
                    <option value="Scaling">Scaling</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Estimated Launch Date:</label>
                <input type="date" name="estimatedLaunch" required>
            </div>
            
            <input type="submit" value="Create Project" class="btn">
            <a href="MainController?action=viewAll" class="btn">Cancel</a>
        </form>
    </div>
</body>
</html>