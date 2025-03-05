<%-- 
    Document   : Home
    Created on : Feb 10, 2025, 1:49:48 PM
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
        <c:if test="${msg!=null}">
            <h2 style="color: red">${msg}</h2>
        </c:if>
            <a href="createstd.jsp">Add new student</a>
        <h1>List Student</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>StudentID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth Of Date</th>
                    <th>Gender</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${requestScope.data}">
                    <tr>
                        <td>${item.getStudentID()}</td>
                        <td>${item.getFirstName()}</td>
                        <td>${item.getLastName()}</td>
                        <td>${item.getDob()}</td>
                        <td>${item.getGender()}</td>
                    </tr>
                </c:forEach>


            </tbody>
        </table>
        
        
        
    </body>
</html>
