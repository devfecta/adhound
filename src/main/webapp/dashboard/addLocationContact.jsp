<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../template/header.jsp" %>

<c:set var="errors" value="${errormessages}" scope="session" />

<section class="container">

    <div class="py-5 text-center">
        <h2>Add Contact</h2>
    </div>

    <div class="row">

        <div class="col-md md-1">

            <c:choose>
                <c:when test="${not empty contactFirstName}">

                    <div class="alert alert-success" role="alert">
                        ${contactFirstName} has been added.
                    </div>

                    <a href="${pageContext.request.contextPath}/dashboard/location?id=${locationId}" class="btn btn-primary p-2" role="button">Locations</a>

                </c:when>
                <c:otherwise>

                    <form id="adhoundForm" action="${pageContext.request.contextPath}/dashboard/location/addContact" method="post" class="needs-validation" novalidate>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstNameTextbox">First Name</label>
                                <input value='${param.firstNameTextbox}' type="text" id="firstNameTextbox" name="firstNameTextbox" class="form-control" required />
                                <div class="invalid-feedback">
                                        ${errormessages.firstNameTextbox}
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="lastNameTextbox">First Name</label>
                                <input value='${param.lastNameTextbox}' type="text" id="lastNameTextbox" name="lastNameTextbox" class="form-control" required />
                                <div class="invalid-feedback">
                                        ${errormessages.lastNameTextbox}
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md mb-3">
                                <label for="emailTextbox">e-Mail Address</label>
                                <input value='${param.emailTextbox}' type="text" id="emailTextbox" name="emailTextbox" class="form-control" required />
                                <div class="invalid-feedback">
                                        ${errormessages.emailTextbox}
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
                                <label for="contactTypeDropdown">Region</label>
                                <select required type="text" id="contactTypeDropdown" name="contactTypeDropdown" class="form-control">
                                    <option value="1" <c:if test="${param.contactType eq '1'}">selected</c:if>>Main Contact</option>
                                    <option value="2" <c:if test="${param.contactType eq '2'}">selected</c:if>>Secondary Contact</option>
                                    <option value="3" <c:if test="${param.contactType eq '3'}">selected</c:if>>Emergency Contact</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md mb-3">
                                <button type="submit" class="btn btn-primary btn-lg btn-block" role="button">Add Contact</button>
                            </div>
                        </div>

                        <input type="hidden" value="${locationId}" id="locationId" name="locationId" />

                    </form>

                </c:otherwise>
            </c:choose>

        </div>

    </div>

</section>

<%@ include file="../template/footer.jsp" %>

