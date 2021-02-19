<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page import="com.conference.my.model.entity.Location" %>

<html>

<c:set var="title" value="conferenses"/>
<c:set var="background" value="'https://mdbootstrap.com/img/new/slides/040.jpg'" />
<c:set var="height" value="200px" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%--<jsp:include page="/WEB-INF/jspf/header.jspf" />--%>

<>
    <c:set var="now" value="<%= java.time.LocalDate.now() %>"/>
    <c:out value="Today is: ${now}"/>

    <h1>EVENTS VISITOR Page</h1>
    <%--    <c:set var="events" value="${requestScope.events}"/>--%>
    <div class="d-flex justify-content-end">
        <button type="submit" class="btn btn-outline-primary disabled">Apply to all:</button>
    </div>

    <div class="container-md col-md-5 mx-auto text-center">
        <c:forEach items="${events}" var="event">
            <div
            <c:if test="${user.isEventVisited(event.id) == true}">
                class="bg-light"
            </c:if>
            >
                <h2><c:out value="${event.title}"/></h2>
                <h4><c:out value="${event.date}"/></h4>
                <h5><c:out value="${event.location}"/></h5>
                <c:if test="${event.hasVisitor(user.id) == false}">
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="registerUserForEvent">
                        <input type="hidden" name="eventId" value="${event.id}">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit" class="btn btn-dark">apply for visiting</button>
                    </form>
                </c:if>

                <c:if test="${(event.hasVisitor(user.id) == true) && (user.isEventVisited(event.id) == false)}">
                    <button type="reset"
                            class="btn btn-outline-light btn-lg">you are subscribed to this event
                    </button>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="visitEvent"/>
                        <input type="hidden" name="eventId" value="${event.id}">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit"
                                class="btn btn-success btn-lg"
                                <c:if test="${event.date != now}">
                                    disabled
                                </c:if>
                        >visit event
                        </button>
                    </form>
                </c:if>

                <c:if test="${user.isEventVisited(event.id) == true}">
                    <button type="reset"
                            class="btn btn-outline-success btn-lg">You have already visited this event
                    </button>
                </c:if>

                <form method="post" action="controller">
                    <input type="hidden" name="command" value="eventDetails">
                    <input type="hidden" name="eventId" value="${event.id}">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" class="btn btn-dark">event Details...</button>
                </form>

                <hr>
            </div>
        </c:forEach>

    </div>

</main>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/script.jspf" %>
</body>
</html>
