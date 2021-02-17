<%@ include file="/WEB-INF/jspf/page.jspf"%>
<html>

<c:set var="title" value="conferenses" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%--<jsp:include page="/WEB-INF/jspf/header.jspf" />--%>

<main>
<%--    <c:out value="<%= new java.util.Date() %>"/>--%>
    <c:set var="now" value="<%= java.time.LocalDate.now() %>"/>
    <c:out value="Today is: ${now}"/>
<%--    <jsp:useBean id="now" class="java.util.Date"  />--%>
<%--    <fmt:formatDate value="${now}"  pattern="yyyy-MM-dd"/>--%>

    <h1>EVENTS VISITOR Page</h1>
    <c:set var="events" value="${requestScope.events}"/>
<%--    <c:set var="registered" value="${sessionScope.registered}"/>--%>

    <div class="container-md col-md-5 mx-auto text-center">
        <!-- Modal -->

        <c:forEach items="${events}" var="event">
            <h2><c:out value="${event.title}"/></h2>
            <h4><c:out value="${event.date}"/></h4>
            <%--    TODO licationId -> Location--%>
            <%--            <h5><c:out value="${event.Location}"/></h5>--%>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="registerUserForEvent">
                <input type="hidden" name="eventId" value="${event.id}">
                <input type="hidden" name="userId" value="${user.id}">
                <button type="submit" class="btn btn-dark">apply for visiting</button>
            </form>
            <c:if test="${event.hasUser(user.id) == true}">
                <%--        <form method="post" action="controller">--%>
                <%--            <input type="hidden" name="command" value="register">--%>
            <button type="reset"
                    class="btn btn-outline-light btn-lg">you are subscribed to this event</button>
                <%--        </form>--%>
            </c:if>

            <hr>
        </c:forEach>

    </div>

</main>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/script.jspf"%>
</body>
</html>
