<%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 2017-02-27
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title>Log Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
    </head>

    <body>
        <div class="container" style="padding: 15% 20%;">
            <form action="<c:url value="/login"/>" method="post" style="width:300px" action="http://www.javatpoint.com/javascriptpages/valid.jsp">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-group">
                    <label for="userLogin">Name</label>
                    <input name="username" type="text" class="form-control" id="userLogin" value="">
                </div>

                <div class="form-group">
                    <label for="userPassword">Password</label>
                    <input name="password" class="form-control" id="userPassword" type="password" value="">
                </div>
                <button type="submit" class="btn btn-default" style="float: left; margin-right: 10px; width: 200px;">Login</button>
            </form>
            <button class="btn btn-default" style="float: left;">
                <a href="<spring:url value="/register"/>">
                    Register
                </a>
            </button>
            <div style="clear: both"/>
            <c:if test="${mistake == 'true'}">
                <div class="alert alert-danger">
                    <strong>Your login/password is wrong</strong>
                </div>
            </c:if>
        </div>
    </body>
</html>
