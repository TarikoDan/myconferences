<%@ include file="/WEB-INF/jspf/page.jspf" %>

<html>

<c:set var="title" value="event - ${event.title}" />
<c:set var="now" value="<%= java.time.LocalDate.now() %>" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>



<!-- Pills navs -->
<div class="container-md col-md-6 mx-auto p-5">
    <c:out value="${event.title}"/>
    <c:out value="${event.date}"/>
<%--    TODO licationId -> Location--%>
<%--    <c:out value="${event.location}"/>--%>


    <h3>You can visit this Event on ${event.date.compareTo(now)} days</h3>
    <h3>You can visit this Event on ${event.date.toEpochDay()-now.toEpochDay()} days</h3>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="visitEvent"/>
        <!-- Submit button -->
        <button type="submit"
                class="btn btn-dark btn-block mb-4"
                <c:if test="${event.date != now}">
                    disabled
                </c:if>
        >visit event</button>

        <!-- Register buttons -->
    </form>


    <c:if test="${event.date == now}">
        <h3>visit</h3>
<%--        <form method="post" action="controller">--%>
<%--            <input type="hidden" name="command" value="register">--%>
<%--            <button type="submit" class="btn btn-outline-light btn-lg">Register!</button>--%>
<%--        </form>--%>
    </c:if>



</div>

    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    <%@ include file="/WEB-INF/jspf/script.jspf"%>

</body>
</html>
