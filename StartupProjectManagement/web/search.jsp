<%-- 
    Document   : search
    Created on : Mar 2, 2025, 5:25:37 PM
    Author     : thanh
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO)request.getAttribute("user");
            
        
        
        %>
        
       <h1> Wellcome <%=user.getName()%> </h1>
       <a href="MainController?action=logout"> Logout
           
           
       </a>
        
        <form action="#">
            Search Value: <input type="submit" name="txtSearchValue"/><br/>
            <input type="submit" name="search"/>
        </form> <h1>Hello World!</h1>
    </body>
</html>
