
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giải phương trình bậc 2</title>
    </head>
    <body>
        <form action="controller" method="get"> 
            <h1>Giải phương trình bậc 2: ax² + bx + c = 0</h1>
            a: <input type="number" step="any" name="a" value="${a}" required/><br>
            b: <input type="number" step="any" name="b" value="${b}" required/><br>
            c: <input type="number" step="any" name="c" value="${c}" required/><br>
            <input type="submit" value="Tính" />
        </form>
        
        <c:if test="${error != null}">
            <h3 style="color: red">${error}</h3>
        </c:if>
        
        <c:if test="${data != null}">
            <h3>Kết quả:</h3>
            
            <p style="color: blue">${data.msg}</p>
            
          
            
            <c:if test="${data.infor.flag == 2}">
                <p>x1 = ${data.infor.x1}</p>
                <p>x2 = ${data.infor.x2}</p>
            </c:if>
            
            
        </c:if>
    </body>
</html>