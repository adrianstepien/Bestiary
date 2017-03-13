<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
      <title>Main Page</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
      <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
<body>


    <div class="container" style="padding-top: 10px;">
        <h3 style="float: left;">
            <span class="label label-info">
                Login: <security:authentication property="principal.username" />
            </span>
        </h3>
        <button class="btn btn-danger" style="float: right;">
            <a href="<c:url value="/logOut"/>" >
                Log Out
            </a>
        </button>
        <security:authorize access="hasAnyRole('admin')">
            <button class="btn btn-success" style="float: right;">
                <a href="<c:url value="/admin/add"/>" >
                    Create beast
                </a>
            </button>
        </security:authorize>
        <div style="clear: both"/>
        <div class="page-header">
            <h1><center>Bestiary</center></h1>
        </div>
    </div>

    <div class="container">
      <c:forEach items="${beasts}" var="beast">
        <div class="jumbotron">
            <div style="float: left">
                <img src="<c:url value="/resource/images/${beast.beastId}.jpg"/>" class="img-rounded" alt="abc" width="300" height="250">
            </div>
            <div style="float: left; padding-left: 80px;" >
                <h2>Name:  ${beast.name}</h2>
                <p>
                    <ul>
                        <li>category: ${beast.category}</li>
                        <li>ID: ${beast.beastId}</li>
                    </ul>
                </p>

                <button class="btn btn-default">
                    <a href="<spring:url value="/beast/details?id=${beast.beastId}"/>">
                        Details
                    </a>
                </button>

                <security:authorize access="hasAnyRole('admin')">
                    <button class="btn btn-default" style="background-color: goldenrod;" >
                        <a href="<spring:url value="/beast/update?id=${beast.beastId}"/>" >
                            Edit
                        </a>
                    </button>
                </security:authorize>

                <security:authorize access="hasAnyRole('admin')">
                    <button class="btn btn-default" style="background-color: brown" >
                        <a href="<spring:url value="/beast/delete?id=${beast.beastId}"/>" >
                            Delete
                        </a>
                    </button>
                </security:authorize>
            </div>
            <div style="clear: left"></div>
        </div>
      </c:forEach>
    </div>
</body>
</html>
