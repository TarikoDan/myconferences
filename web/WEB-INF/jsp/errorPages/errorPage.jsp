<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page isErrorPage="true" %>

<html>

<c:set var="title" value="Error" scope="page" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table class="container-md col-6 mx-auto mt-5">

    <%-- HEADER --%>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <%-- HEADER --%>

    <tr >
        <td >

            <h2 class="text-danger bg-light">The following error occurred</h2>

            <c:set var="errorMessage" value="${requestScope.errorMessage}"/>
            <c:set var="errorCode" value="${requestScope.errorCode}"/>

<%--            <c:if test="${not empty code}">--%>
<%--                <h3>Error code: ${code}</h3>--%>
<%--            </c:if>--%>

            <%-- if get this page using forward --%>
            <c:if test="${not empty errorMessage}">
                <h4 class="text-light bg-danger">Error message: ${errorMessage}</h4>
            </c:if>
            <c:if test="${not empty errorCode}">
                <h4 class="text-light bg-danger">Error Code: ${errorCode}</h4>
            </c:if>
            <c:if test="${empty errorMessage}">
                <h3 class="text-danger">Error message: Server side ERROR occurred, please reload the command</h3>
            </c:if>

        </td>
    </tr>
</table>

<div class="text-center mt-5 col-md-5 mx-auto">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="loginPage"/>
        <button class="btn btn-warning btn-block mb-4">
            BACK to LogIN page
        </button>
    </form>

    <a class="btn btn-success btn-block mb-4"
       href="${pageContext.request.contextPath}">
        BACK to home page
    </a>

</div>

    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
