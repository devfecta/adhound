<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="./css/bootstrap/4.4.1/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/font-awesome/all.min.css" />
    <link rel="stylesheet" href="./css/main.css" />

    <style>

        html,
        body {
            height: 100%;
        }

        body {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-align: center;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #fff;
        }

        @media (max-width: 767px) {
            .login-form {
                width: 90%;
                padding: 15px;
                margin: auto;
            }
        }

    </style>

</head>
<body>

<div class="row h-100 mx-auto">
    <div class="col-sm-12 my-auto d-block text-center">
        <img class="brand-logo mb-2" src="./images/AdHound-Logo-Square.jpg" alt="AdHound">

        <h5 class="text-danger">Invalid username and/or password.</h5>

        <a href="login" class="btn btn-secondary btn-block my-3" role="button">Login</a>
    </div>
</div>




<script src="./js/jquery-3.4.1.min.js"></script>
<script src="./js/popper/popper.js"></script>
<script src="./css/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>