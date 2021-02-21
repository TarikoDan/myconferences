<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page import="com.conference.my.model.entity.Location" %>

<html>
<c:set var="title" value="event - ${event.title}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<c:set var="daysLeft" value="${event.date.toEpochDay()-now.toEpochDay()}"/>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/info.jspf" %>

<div class="container-md col-md-6 mx-auto p-5 ">
    <c:if test="${event.hasVisitor(user.id) == true}">
        <h5 class="mb-3"><span class="badge bg-secondary text-dark">You are subscribed to this event</span></h5>
    </c:if>
    <c:if test="${user.isEventVisited(event.id) == true}">
        <h5 class="mb-3"><span class="badge bg-success text-dark">You have already visited this event</span></h5>
    </c:if>

    <h1><c:out value="${event.title}"/></h1>
    <h3><c:out value="${event.date}"/></h3>

    <ul class="list-group list-group-flush bg-transparent">
        <li class="list-group-item bg-transparent"><span
                class="badge bg-dark col-2">zipCode</span> ${event.location.zipCode}</li>
        <li class="list-group-item bg-transparent"><span
                class="badge bg-dark col-2">country</span> ${event.location.country}</li>
        <li class="list-group-item bg-transparent"><span
                class="badge bg-dark col-2">region</span> ${event.location.region}</li>
        <li class="list-group-item bg-transparent"><span class="badge bg-dark col-2">city</span> ${event.location.city}
        </li>
        <li class="list-group-item bg-transparent"><span
                class="badge bg-dark col-2">street</span> ${event.location.street}</li>
        <li class="list-group-item bg-transparent"><span
                class="badge bg-dark col-2">building</span> ${event.location.building}</li>
        <li class="list-group-item bg-transparent"><span
                class="badge bg-dark col-2">suite</span> ${event.location.suite}</li>
    </ul>

    <%--    <table class="table table-responsive-sm">--%>
    <%--        <thead>--%>
    <%--        <tr>--%>
    <%--            <th colspan="2" scope="col">Location:</th>--%>
    <%--        </tr>--%>
    <%--        </thead>--%>
    <%--        <tbody>--%>
    <%--        <tr>--%>
    <%--            <th scope="row">zipCode</th>--%>
    <%--            <td>${event.location.zipCode}</td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <th scope="row">country</th>--%>
    <%--            <td>${event.location.country}</td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <th scope="row">region</th>--%>
    <%--            <td>${event.location.region}</td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <th scope="row">city</th>--%>
    <%--            <td>${event.location.city}</td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <th scope="row">street</th>--%>
    <%--            <td>${event.location.street}</td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <th scope="row">building</th>--%>
    <%--            <td>${event.location.building}</td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <th scope="row">suite</th>--%>
    <%--            <td>${event.location.suite}</td>--%>
    <%--        </tr>--%>
    <%--        </tbody>--%>
    <%--    </table>--%>

    <div class="text-center mb-3">
        <c:if test="${event.hasVisitor(user.id) == false}">
            <form method="post" action="controller">
                <input type="hidden" name="command" value="registerUserForEvent">
                <input type="hidden" name="eventId" value="${event.id}">
                <input type="hidden" name="userId" value="${user.id}">
                <button type="submit" class="btn btn-lg btn-block btn-secondary">apply for visiting</button>
            </form>
        </c:if>

        <c:if test="${(event.hasVisitor(user.id) == true) && (user.isEventVisited(event.id) == false)}">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="visitEvent"/>
                <input type="hidden" name="eventId" value="${event.id}">
                <input type="hidden" name="userId" value="${user.id}">
                <button type="submit"
                        class="btn btn-lg btn-block btn-success my-5"
                        <c:if test="${event.date != now}">
                            disabled
                        </c:if>>

                    <c:choose>
                        <c:when test="${daysLeft == 0}">
                            <span class="text-light">You can visit this Event today!</span>
                        </c:when>
                        <c:otherwise>
                            <span class="text-danger">You can visit this Event in ${daysLeft} days</span>
                        </c:otherwise>
                    </c:choose>
                </button>
            </form>
        </c:if>

        <c:if test="${user.isEventVisited(event.id) == true}">
            <h3>Thank you for visiting our EVENT!</h3>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="rateEvent">
                <input type="hidden" name="eventId" value="${event.id}">
                <input type="hidden" name="userId" value="${user.id}">
                <button type="submit" class="btn btn-lg btn-warning mb-5">Rate Event</button>
            </form>
        </c:if>

        <a class="btn btn-info mb-4"
           href="${pageContext.request.contextPath}">
            BACK to home page
        </a>

        <form method="post" action="controller" class="my-0 p-2">
            <input type="hidden" name="command" value="navEvents"/>
            <button type="submit" class="btn btn-success my-auto">BACK to Events</button>
        </form>


    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/script.jspf" %>

</body>
</html>
