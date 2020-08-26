<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>

    <link rel="stylesheet" href="./css/bootstrap/4.4.1/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/font-awesome/all.min.css" />
    <link rel="stylesheet" href="./css/main.css" />

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .container {
            max-width: 960px;
        }

        .lh-condensed { line-height: 1.25; }

    </style>

</head>
<body class="text-center">

    <section class="container">

        <div class="py-5 text-center">
            <img class="d-block mx-auto mb-4" src="./images/AdHound-Logo-Square.jpg" alt="AdHound" width="72" height="72">
            <h2>Registration</h2>
        </div>

        <div class="row">

            <div class="col-md md-1">

                <c:choose>
                    <c:when test="${not empty registrantFirstName}">

                        <div class="alert alert-success" role="alert">
                            Thank you ${registrantFirstName} for registering with us.
                        </div>

                        <a href="dashboard" class="btn btn-primary p-2" role="button">Login</a>

                    </c:when>
                    <c:otherwise>

                        <form action="register" method="post" class="needs-validation" novalidate>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="emailTextbox">e-Mail Address</label>
                                    <input value='<c:if test="${empty errormessages.username}">${param.emailTextbox}</c:if>' required type="email" id="emailTextbox" name="emailTextbox" class="form-control" placeholder="you@example.com" />
                                    <div class="invalid-feedback">
                                            ${errormessages.username}
                                    </div>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="passwordTextbox">Password</label>
                                    <input required type="password" id="passwordTextbox" name="passwordTextbox" class="form-control" />
                                    <div class="invalid-feedback">
                                            ${errormessages.password}
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="firstNameTextbox">First Name</label>
                                    <input value='${param.firstNameTextbox}' required type="text" id="firstNameTextbox" name="firstNameTextbox" class="form-control" />
                                    <div class="invalid-feedback">
                                            ${errormessages.firstName}
                                    </div>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="lastNameTextbox">Last Name</label>
                                    <input value='${param.lastNameTextbox}' required type="text" id="lastNameTextbox" name="lastNameTextbox" class="form-control" />
                                    <div class="invalid-feedback">
                                            ${errormessages.lastName}
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="phoneTextbox">Phone</label>
                                    <input value='${param.phoneTextbox}' required type="text" id="phoneTextbox" name="phoneTextbox" class="form-control" />
                                    <div class="invalid-feedback">
                                            ${errormessages.phone}
                                    </div>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="faxTextbox">Fax</label>
                                    <input value='${param.faxTextbox}' type="text" id="faxTextbox" name="faxTextbox" class="form-control" />
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md mb-3">
                                    <label for="addresssTextbox">Street Address</label>
                                    <input value='${param.addresssTextbox}' required type="text" id="addresssTextbox" name="addresssTextbox" class="form-control" />
                                    <div class="invalid-feedback">
                                            ${errormessages.address}
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-5 mb-3">
                                    <label for="cityTextbox">City</label>
                                    <input value='${param.cityTextbox}' required type="text" id="cityTextbox" name="cityTextbox" class="form-control" />
                                    <div class="invalid-feedback">
                                            ${errormessages.city}
                                    </div>
                                </div>
                                <div class="col-md-3 mb-3">
                                    <label for="stateIdDropdown">State</label>
                                    <select required type="text" id="stateIdDropdown" name="stateIdDropdown" class="form-control">
                                        <c:forEach var="state" items="${states}">
                                            <option value="${state.id}" <c:if test="${param.stateIdDropdown eq state.id}">selected</c:if>>${state.abbreviation}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label for="zipcodeTextbox">Zipcode</label>
                                    <input value='${param.zipcodeTextbox}' required type="text" id="zipcodeTextbox" name="zipcodeTextbox" class="form-control" />
                                    <div class="invalid-feedback">
                                            ${errormessages.zipcode}
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md mb-3">
                                    <button type="submit" class="btn btn-primary btn-lg btn-block" role="button">Register</button>
                                </div>
                            </div>

                        </form>

                    </c:otherwise>
                </c:choose>

            </div>

        </div>

    </section>

    <script src="./js/jquery-3.4.1.min.js"></script>
    <script src="./js/popper/popper.js"></script>
    <script src="./css/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <c:if test="${fn:length(errormessages) gt 0}">
        <script>
            let form = document.querySelector("form");
            form.classList.add('was-validated');
        </script>
    </c:if>

</body>
</html>