<%-- 
    Document   : login
    Created on : 26-10-2014, 11:48:08
    Author     : miguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h3> Please login:</h3>
        <form action="j_security_check" method=post>
            <p>username: <input type="text" name="j_username"></p>
            <p>password: <input type="password" name="j_password"></p>
            <input type="submit" value="submit">
            <input type="reset" value="Reset"> 
            
        </form>
    </body>
</html>