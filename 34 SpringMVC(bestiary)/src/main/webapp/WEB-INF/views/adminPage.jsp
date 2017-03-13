<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create beast</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
    <center>
        <div class="jumbotron">
            <h1>Create beast</h1>
        </div>
    </center>

    <div class="container">
        <button class="btn" style="float: right; ">
            <a href="<spring:url value="/beast/show"/>">
                Main Page
            </a>
        </button>
    </div>

    <div style="width: 50%; margin: auto;">
        <form:form modelAttribute="newBeast" style="width:300px" enctype="multipart/form-data">
            <form:hidden path="beastId"/>

            <div class="form-group">
                <label for="name">Name</label>
                <form:input path="name" type="text" class="form-control" id="name" value="${newBeast.name}"/>
                <form:errors path="name" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="strength">Strength</label>
                <form:input path="strength" type="text" class="form-control" id="strength" value="${newBeast.strength}"/>
                <form:errors path="strength" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="hp">Hp</label>
                <form:input path="hp" type="text" class="form-control" id="hp"/>
                <form:errors path="hp" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="category">Category</label>
                <form:input path="category" type="text" class="form-control" id="category"/>
                <form:errors path="category" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="description">Description</label>
                <form:input path="description" type="text" class="form-control" id="description"/>
                <form:errors path="description" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="image">Image</label>
                <form:input path="image" type="file" class="form-control" id="image"/>
            </div>

            <c:if test="${update == 'false'}">
                <button type="submit" class="btn btn-default">
                    Add Beast
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </button>
            </c:if>
            <c:if test="${update == 'true'}">
                <button type="submit" class="btn btn-default">
                    Edit
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </button>
            </c:if>
        </form:form>
    </div>
</body>
</html>
