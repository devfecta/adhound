<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../template/header.jsp" %>

<c:set var="errors" value="${errormessages}" scope="session" />

<section class="container">

    <div class="py-5 text-center">
        <h2>Add Location</h2>
    </div>

    <div class="row">

        <div class="col-md md-1">

            <c:choose>
                <c:when test="${not empty locationName}">

                    <div class="alert alert-success" role="alert">
                        ${locationName} has been added.
                    </div>

                    <a href="${pageContext.request.contextPath}/dashboard/locations" class="btn btn-primary p-2" role="button">Locations</a>

                </c:when>
                <c:otherwise>

                    <form id="adhoundForm" action="${pageContext.request.contextPath}/dashboard/addLocation" method="post" class="needs-validation" novalidate>

                        <div class="row">
                            <div class="col-md mb-3">
                                <label for="nameTextbox">Location Name</label>
                                <input value='${param.nameTextbox}' type="text" id="nameTextbox" name="nameTextbox" class="form-control" required />
                                <div class="invalid-feedback">
                                        ${errormessages.name}
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="phoneTextbox">Phone</label>
                                <input value='${param.phoneTextbox}' type="text" id="phoneTextbox" name="phoneTextbox" class="form-control" />
                                <div class="invalid-feedback">

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
                                <input value='${param.addresssTextbox}' type="text" id="addresssTextbox" name="addresssTextbox" class="form-control" />
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
                                <label for="regionIdDropdown">Region</label>
                                <select required type="text" id="regionIdDropdown" name="regionIdDropdown" class="form-control">
                                    <c:forEach var="region" items="${regions}">
                                        <option value="${region.id}" <c:if test="${param.regionIdDropdown eq region.id}">selected</c:if>>${region.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md mb-3">
                                <button type="submit" class="btn btn-primary btn-lg btn-block" role="button">Add Location</button>
                            </div>
                        </div>

                    </form>

                </c:otherwise>
            </c:choose>

        </div>

    </div>

</section>

<%@ include file="../template/footer.jsp" %>

