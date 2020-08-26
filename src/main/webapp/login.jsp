<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/4.4.1/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome/all.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />

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

        .login-form {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
        .login-form .checkbox {
            font-weight: 400;
        }
        .login-form .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }
        .login-form .form-control:focus {
            z-index: 2;
        }
        .login-form input[type="text"] {
            margin-bottom: 1em;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

        .brand-logo {

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
<body class="text-center">

<form class="login-form" action="${pageContext.request.contextPath}/j_security_check" method="post">
    <img class="brand-logo mb-2 w-100" src="${pageContext.request.contextPath}/images/AdHound-Logo-Square.jpg" alt="AdHound">

    <label for="usernameTextbox" class="sr-only">Username</label>
    <input type="text" id="usernameTextbox" name="j_username" value="kkelm@email.com" class="form-control mb-3" placeholder="Username" required autofocus />

    <label for="passwordTextbox" class="sr-only">Password</label>
    <input type="password" id="passwordTextbox" name="j_password" class="form-control mb-3" value="123abc" placeholder="Password" required />

    <button type="submit" class="btn btn-primary btn-block mb-3" role="button">Login</button>

    <a href="register" class="btn btn-secondary btn-block" role="button">Register</a>

    <p class="mt-4 text-muted">&copy; 2020</p>

</form>

<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper/popper.js"></script>
<script src="${pageContext.request.contextPath}/css/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>