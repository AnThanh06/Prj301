<%-- 
    Document   : Createst
    Created on : Feb 10, 2025, 1:42:16 PM
    Author     : thanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ADD Student</h1>
        <form action="StudentController" method="post">
            StudentID: <input type="number" name="id" value=""/>
            First Name: <input type="text" name="fname" value=""/>
            Last name: <input type="text" name="fname" value=""/>
            Date of Birth: <input type="date" name="dob" value=""/>
            Gender: <input type="number" name="gender" value=""/>
            <input type="submit" value="add student"/>
        </form>
        <c:if test="${mgs!=null}" >
            <h2  style="color: red"> ${smg}   </h2>
            
        </c:if>
    </body>
</html>
