<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl">
    <head>
        <title>Details</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="/resource/js/usersAdmin.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
<body onload="showAll(${beast.beastId}, '<security:authentication property="principal.username" />')">
    <div class="container" style="padding-top: 10px;">
        <p style="float: right;">
            <button class="btn btn-primar">
                <a href="<spring:url value="/beast/show"/>">
                    Main Page
                </a>
            </button>
            <button class="btn btn-danger" >
                <a href="<spring:url value="/logOut"/>" >
                    Log Out
                </a>
            </button>
        </p>
        <div class="page-header" >
            <h1><center>${beast.name}</center></h1>
        </div>

    </div>

    <div class="container">
        <center>
            <img src="<c:url value="/resource/images/${beast.beastId}.jpg"/>" class="img-thumbnail" alt="abc" width="300" height="250">
        </center>
    </div>

    <div class="container">
        <div class="jumbotron">
            ${beast.description}
        </div>
    </div>

    <div class="container">
        <div class="jumbotron">
            <table class="table table-condensed">
                <tr><th>Id</th><th>Category</th><th>Strength</th><th>Hp</th></tr>
                <tr><td>${beast.beastId}</td><td>${beast.category}</td><td>${beast.strength}</td><td>${beast.hp}</td></tr>
            </table>
        </div>
    </div>

    <div class="container" >
        <input type="text" class="form-control" id="commentInput"/>
        <button id="commentInputButton" class="btn btn-primar" onclick="sendComment(${beast.beastId}, '<security:authentication property="principal.username" />')">Send Comment</button>
    </div>
    <br>

    <div class="container" >
        <div id="commentsZone">
            <%--  Comments/their buttons are here--%>
        </div>
    </div>
</body>
</html>
