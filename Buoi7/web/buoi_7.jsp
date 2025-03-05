<%-- 
    Document   : buoi_7
    Created on : Feb 10, 2025, 9:58:22 AM
    Author     : thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%! int a = 9; %>
        
        <%
            double b;
            b = Math.sqrt(a);

        %>
        
        Ket qua : sqrt(<%=a%>) = <span sytle="color: red"><%=b%></span>
            
        
    </body>
</html>
