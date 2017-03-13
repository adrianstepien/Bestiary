<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <center>
            <div class="jumbotron">
                <h1>Register new user</h1>
            </div>
        </center>

        <button class="btn" style="float: right; ">
            <a href="<spring:url value="/login"/>">
                Main Page
            </a>
        </button>

        <div style="width: 50%; margin: auto;">
            <form:form modelAttribute="newUser" style="width:300px" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="login">Login</label>
                    <form:input path="login" type="text" class="form-control" id="login"/>
                    <form:errors path="login" cssClass="text-danger"/>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <form:input path="password" type="text" class="form-control" id="password"/>
                    <form:errors path="password" cssClass="text-danger"/>
                </div>

                <button type="submit" class="btn btn-default">
                    Register
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </button>
            </form:form>
            <c:if test="${busy == 'true'}">
                <div class="alert alert-danger">
                    <strong>This login is unavailable</strong>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
