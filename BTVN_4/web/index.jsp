<%-- 
    Document   : index
    Created on : Feb 10, 2025, 1:51:16 PM
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
       
         <a href="StudentController">Quản lí học sinh</a>
        <h1>May tinh</h1>
        <form action="Cal">
            Enter a: <input type="number" name="a" value="" /> <br>
            Enter b: <input type="number" name="b" value="" /><br>
            Enter c: <input type="number" name="c" value="" /><br>
            <input type="submit" value="submit" />
        </form>
        
        <c:set var="data" value="${requestScope.data}"></c:set>
        <c:set var="x1" value="${data.getInfo().getX1()}"></c:set>
        <c:set var="x2" value="${data.getInfo().getX2()}"></c:set>
        <c:set var="msg" value="${data.getMsg()}"></c:set>
        <c:set var="flag" value="${data.getInfo().getFlag()}"></c:set>
        <c:if test="${msg!=null}">
            <h2 style="color: red">${msg}</h2>
        </c:if>
        <c:choose>
            <c:when test="${flag == '1'}">
                <h2>x1 = x2 = ${x1}</h2>
            </c:when>
            <c:when test="${flag == '2'}">
                <h2>x1 = ${x1}; x2 = ${x2}</h2>
            </c:when>
            <c:when test="${flag == '3'}">
                <h2>x = ${x1}</h2>
            </c:when>
        </c:choose>
    </body>
</html>
