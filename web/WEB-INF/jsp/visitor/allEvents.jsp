<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page import="com.conference.my.model.entity.Location" %>

<html>
<c:set var="title" value="conferenses"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/info.jspf" %>

<main>
    <h5>EVENTS VISITOR Page</h5>
    <%--    <c:set var="events" value="${requestScope.events}"/>--%>
    <div class="d-flex justify-content-end">
        <button type="submit" class="btn btn-outline-primary disabled">Apply to all:</button>
    </div>

    <div class="container-md col-md-8 mx-auto text-center">
        <c:forEach items="${events}" var="event">
            <div
                <c:choose>
                    <c:when test="${user.isEventVisited(event.id) == true}">
                        class="bg-light shadow-lg border-success"
                    </c:when>
                    <c:otherwise>
                        class="bg-dark shadow-sm border-light text-light"
                    </c:otherwise>
                </c:choose>
<%--            <c:if test="${user.isEventVisited(event.id) == true}">--%>
<%--                class="bg-light shadow-lg border-success"--%>
<%--            </c:if>--%>
            >
                <h2><c:out value="${event.title}"/></h2>
                <h4><c:out value="${event.date}"/></h4>
                <h6><c:out value="${event.location}"/></h6>
                <c:if test="${event.hasVisitor(user.id) == false}">
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="registerUserForEvent">
                        <input type="hidden" name="eventId" value="${event.id}">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit" class="btn btn-outline-secondary btn-lg">apply for visiting</button>
                    </form>
                </c:if>

                <c:if test="${(event.hasVisitor(user.id) == true) && (user.isEventVisited(event.id) == false)}">
                    <button type="reset"
                            class="btn btn-outline-light btn-lg">you are subscribed to this event
                    </button>
                    <form action="controller" method="post" class="my-3">
                        <input type="hidden" name="command" value="visitEvent"/>
                        <input type="hidden" name="eventId" value="${event.id}">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit"
                                class="btn btn-success btn-lg"
                                <c:if test="${event.date != now}">
                                    class="btn btn-outline-success btn-lg"
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

                <form method="post" action="controller" class="my-3">
                    <input type="hidden" name="command" value="eventDetails">
                    <input type="hidden" name="eventId" value="${event.id}">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" class="btn btn-info btn-lg">event Details...</button>
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
