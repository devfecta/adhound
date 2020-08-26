<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../template/header.jsp" %>

<section class="container">

    <div class="py-5 text-center">
        <h2>Delete ${locationName}?</h2>
    </div>

    <div class="row">

        <div class="col-md md-1">

            <c:choose>
                <c:when test="${not empty locationName}">

                    <form id="adhoundForm" action="${pageContext.request.contextPath}/dashboard/deleteLocation?id=${locationId}" method="post">
                        <div class="alert alert-danger" role="alert">
                            <button type="submit" id="updateButton" class="btn btn-success btn-lg btn-block" role="button">Delete Location</button>
                        </div>
                    </form>

                </c:when>
                <c:otherwise></c:otherwise>
            </c:choose>

        </div>
    </div>

</section>

<%@ include file="../template/footer.jsp" %>