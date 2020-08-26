<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${title}</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap/4.4.1/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome/css/all.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />

</head>
<body class="bg-light">



<nav class="navbar navbar-expand-xl navbar-light fixed-top">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard"><img src="${pageContext.request.contextPath}/images/AdHound-Logo.gif" alt="AdHound" class="img-responsive" style="max-height: 30px" /></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon mb-1"></span>
    </button>

    <div class="row collapse navbar-collapse my-2" id="navbarsExampleDefault">
        <div id="dashboardDiv" class="col-md-2 d-flex justify-content-start align-items-center">
            <a class="nav-link stretched-link" href="${pageContext.request.contextPath}/dashboard"><span class="fas fa-tachometer-alt"></span> Dashboard</a> <span class="sr-only">(current)</span>
        </div>
        <div id="searchDiv" class="col-md-8">
            <form class="row my-2 mx-2">
                <input class="col-9 form-control" type="text" placeholder="Search" aria-label="Search">
                <button class="col-3 form-control btn btn-secondary" type="submit">Search</button>
            </form>
        </div>
        <div id="logoutDiv" class="col-md-2 d-flex justify-content-end align-items-center">
            <a class="nav-link stretched-link" href="${pageContext.request.contextPath}/logout.jsp">Logout <span class="fas fa-sign-out-alt"></span></a>
        </div>
    </div>
</nav>


<main class="container p-2" style="margin-bottom: 10%">