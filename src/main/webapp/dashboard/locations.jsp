<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../template/header.jsp" %>

<section>

    <div class="row">
        <div class="col-md">
            <a href="${pageContext.request.contextPath}/dashboard/addLocation" class="col-xs flex-fill m-1 float-right">
                <span class="fas fa-plus-square" style="font-size: 3vw;"></span>
            </a>
        </div>
    </div>

    <div class="list-group">

        <c:choose>

            <c:when test="${fn:length(locations) gt 0}">


                <c:forEach var="location" items="${locations}">

                    <a href="${pageContext.request.contextPath}/dashboard/location?id=${location.id}" class="row d-flex m-0 text-justify list-group-item list-group-item-action align-items-start justify-content-between">
                        <div class="col-lg-4 col-md-6">
                            ${location.name}
                        </div>
                        <div class="col-lg-5 col-md-6">
                            ${location.address}<br /> ${location.city}, ${location.state.abbreviation} ${location.zipcode}<br />
                            <small>Region: ${location.region.name}</small>
                        </div>
                        <div class="col-lg-3 col-md">
                            Phone:&nbsp;${location.phone}<br />
                            Fax:&nbsp;${location.fax}
                        </div>
                    </a>
                </div>
                </c:forEach>

            </c:when>
            <c:otherwise>
                <div class="list-group-item">
                    <h5 class="mb-1">No Locations Found</h5>
                </div>
            </c:otherwise>

        </c:choose>

    </div>
</section>

<%@ include file="../template/footer.jsp" %>