<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../template/header.jsp" %>

<c:set var="errors" value="${errormessages}" scope="session" />

<c:set var="contacts" scope="page" value=""/>

<section class="container">

    <div class="py-5 text-center">
        <h2>${location.name}</h2>
    </div>

    <div class="row">

        <div class="col-md-7 md-1">

            <c:choose>
                <c:when test="${not empty locationName}">

                    <div class="alert alert-success" role="alert">
                        ${locationName} has been updated.
                    </div>

                    <a href="${pageContext.request.contextPath}/dashboard/locations" class="btn btn-primary p-2" role="button">Locations</a>

                </c:when>
                <c:otherwise>

                    <c:set var="contacts" scope="page" value="${location.locationContacts}"/>
                    <c:set var="locationId" scope="page" value="${location.id}"/>

                    <form id="adhoundForm" action="${pageContext.request.contextPath}/dashboard/location?id=${location.id}" method="post" class="needs-validation" novalidate>

                        <div class="row">
                            <div class="col-md mb-3">
                                <label for="nameTextbox">Location Name</label>
                                <input value='${location.name}' type="text" id="nameTextbox" name="nameTextbox" class="form-control" required disabled />
                                <div class="invalid-feedback">
                                        ${errormessages.name}
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="phoneTextbox">Phone</label>
                                <input value='${location.phone}' type="text" id="phoneTextbox" name="phoneTextbox" class="form-control" disabled />
                                <div class="invalid-feedback">

                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="faxTextbox">Fax</label>
                                <input value='${location.fax}' type="text" id="faxTextbox" name="faxTextbox" class="form-control" disabled />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md mb-3">
                                <label for="addresssTextbox">Street Address</label>
                                <input value='${location.address}' type="text" id="addresssTextbox" name="addresssTextbox" class="form-control" disabled />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-5 mb-3">
                                <label for="cityTextbox">City</label>
                                <input value='${location.city}' type="text" id="cityTextbox" name="cityTextbox" class="form-control" required disabled />
                                <div class="invalid-feedback">
                                        ${errormessages.city}
                                </div>
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="stateIdDropdown">State</label>
                                <select required type="text" id="stateIdDropdown" name="stateIdDropdown" class="form-control" disabled>
                                    <c:forEach var="state" items="${states}">
                                        <option value="${state.id}" <c:if test="${location.stateId eq state.id}">selected</c:if>>${state.abbreviation}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="zipcodeTextbox">Zipcode</label>
                                <input value='${location.zipcode}' type="text" id="zipcodeTextbox" name="zipcodeTextbox" class="form-control" required disabled />
                                <div class="invalid-feedback">
                                        ${errormessages.zipcode}
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md mb-3">
                                <label for="regionIdDropdown">Region</label>
                                <select required type="text" id="regionIdDropdown" name="regionIdDropdown" class="form-control" disabled>
                                    <c:forEach var="region" items="${regions}">
                                        <option value="${region.id}" <c:if test="${location.regionId eq region.id}">selected</c:if>>${region.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md mb-3">
                                <button type="button" onclick="editForm(document.forms.adhoundForm.elements)" class="btn btn-primary btn-lg btn-block" role="button">Edit Location</button>
                            </div>
                            <div class="col-md mb-3">
                                <button type="button" onclick="window.location='${pageContext.request.contextPath}/dashboard/deleteLocation?id=${location.id}'" class="btn btn-danger btn-lg btn-block d-none" role="button">Delete Location</button>
                            </div>
                            <div class="col-md mb-3">
                                <button type="submit" id="updateButton" class="btn btn-success btn-lg btn-block d-none" role="button">Update Location</button>
                            </div>
                            <div class="col-md mb-3">
                                <button type="button" onclick="editForm(document.forms.adhoundForm.elements)" id="cancelButton" class="btn btn-secondary btn-lg btn-block d-none" role="button">Cancel</button>
                            </div>
                        </div>

                    </form>

                </c:otherwise>
            </c:choose>

        </div>

        <div class="col-md-5 md-1 text-left">
            <div class="row">
                <div class="col-md-7">
                    <h3>Location Contacts</h3>
                </div>
                <div class="col-md-5">
                    <a href="${pageContext.request.contextPath}/dashboard/location/addContact?location=${locationId}" class="col-xs flex-fill m-1 float-right">
                        <span class="fas fa-plus-square" style="font-size: 1.5vw;"></span>
                    </a>
                </div>
            </div>
            <c:choose>
                <c:when test="${fn:length(contacts) gt 0}">
                    <c:forEach var="contact" items="${contacts}">
                        <div class="row">
                            <div class="col-md list-group">
                                <a href="#" class="list-group-item list-group-item-action">
                                    ${contact.firstName} ${contact.lastName}<br/>
                                    ${contact.phone} ${contact.fax}<br/>
                                    ${contact.email}
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    No Contacts
                </c:otherwise>
            </c:choose>
        </div>

    </div>

</section>

<script src="${pageContext.request.contextPath}/js/main.js" type="text/javascript"></script>

<%@ include file="../template/footer.jsp" %>

