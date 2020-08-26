<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</main>

<footer class="container-fluid fixed-bottom">

    <div class="row d-flex justify-content-around align-items-center">

        <a href="${pageContext.request.contextPath}/dashboard/reports" class="col-xs flex-fill justify-content-stretch m-1 btn btn-footer">
            <span class="fas fa-chart-bar" style="font-size: 3vw"></span>
            <br />Reports
        </a>
        <a href="${pageContext.request.contextPath}/dashboard/locations" class="col-xs flex-fill justify-content-stretch m-1 btn btn-footer">
            <span class="fas fa-map-marked-alt" style="font-size: 3vw;"></span>
            <br />Locations
        </a>
        <a href="${pageContext.request.contextPath}/dashboard/panels" class="col-xs flex-fill justify-content-stretch m-1 btn btn-footer">
            <span class="fas fa-th-large" style="font-size: 3vw;"></span>
            <br />Panels
        </a>
        <a href="${pageContext.request.contextPath}/dashboard/advertisers" class="col-xs flex-fill justify-content-stretch m-1 btn btn-footer">
            <span class="fas fa-address-card" style="font-size: 3vw;"></span>
            <br />Advertisers
        </a>
        <a href="${pageContext.request.contextPath}/dashboard/advertisements" class="col-xs flex-fill justify-content-stretch m-1 btn btn-footer">
            <span class="fas fa-file-image" style="font-size: 3vw;"></span>
            <br />Advertisements
        </a>
    </div>

</footer>

<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/popper/popper.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/css/bootstrap/4.4.1/js/bootstrap.min.js" type="text/javascript"></script>

<c:choose>
    <c:when test="${fn:length(errors) gt 0}">
        <script>
            let form = document.querySelector("#adhoundForm");
            console.log(form);
            form.classList.add('was-validated');
            let v = "";
        </script>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>

</body>
</html>