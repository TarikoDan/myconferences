<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page import="com.conference.my.model.entity.Location" %>

<html>
<c:set var="title" value="conferenses"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/info.jspf" %>

<main>
    <h5>EVENTS SPEAKER Page</h5>
    <div class="d-flex justify-content-end">
        <form method="post" action="controller" class="my-0 p-2">
            <input type="hidden" name="command" value="navEvents"/>
            <input type="hidden" name="sortEvents" value="sortEventsByTitle"/>
            <button type="submit" class="btn btn-primary my-auto btn-sm btn-rounded">sort by TITLE</button>
        </form>
        <form method="post" action="controller" class="my-0 p-2">
            <input type="hidden" name="command" value="navEvents"/>
            <input type="hidden" name="sortEvents" value="sortEventsByDate"/>
            <button type="submit" class="btn btn-primary my-auto btn-sm btn-rounded">sort by DATE</button>
        </form>
        <form method="post" action="controller" class="my-0 p-2">
            <input type="hidden" name="command" value="navEvents"/>
            <input type="hidden" name="sortEvents" value="sortEventsByLocation"/>
            <button type="submit" class="btn btn-primary my-auto btn-sm btn-rounded">sort by Location</button>
        </form>
        <form method="post" action="controller" class="my-0 p-2">
            <input type="hidden" name="command" value="navEvents"/>
            <input type="hidden" name="sortEvents" value="getAllMine"/>
            <button type="submit" class="btn btn-primary my-auto btn-sm btn-rounded">with ME</button>
        </form>

        <button type="submit" class="btn btn-outline-primary disabled">Apply to all:</button>
    </div>

    <div class="container-md col-md-8 mx-auto text-center">
        <c:forEach items="${events}" var="event">
            <div style="background: oldlace; border: 3px solid #fcac3c; border-radius: 5px; margin: 10px"
            <c:if test="${user.isEventParticipated(event.id) == true}">
                class="bg-light shadow-2-strong m-3"
            </c:if>
            >
                <h2><c:out value="${event.title}"/></h2>
                <h4><c:out value="${event.date}"/></h4>
<%--                <h6><c:out value="${event.location}"/></h6>--%>

                <c:if test="${empty event.location}">
                    <h6 class="col-1"><span class="badge bg-dark">Location not specified</span></h6>
                </c:if>
                <c:if test="${not empty event.location}">
                <div class="card-text note border-dark">
                    <ul class="list-group list-group-flush bg-transparent">
                        <div class="d-flex">
                            <h6 class="col-1"><span class="badge bg-dark">zipCode</span></h6> <h5
                                class="col-3 mb-3"> ${event.location.zipCode}</h5>
                            <h6 class="col-1"><span class="badge bg-dark">country</span></h6> <h5
                                class="col-3 mb-3"> ${event.location.country}</h5>
                        </div>
                        <div class="d-flex">
                            <h6 class="col-1"><span class="badge bg-dark">region</span></h6>   <h5
                                class="col-3 mb-3">${event.location.region}</h5>
                            <h6 class="col-1"><span class="badge bg-dark">city</span></h6>     <h5
                                class="col-3 mb-3">${event.location.city}</h5>
                        </div>
                        <div class="d-flex">
                            <h6 class="col-1"><span class="badge bg-dark">street</span></h6>   <h5
                                class="col-3 mb-3">${event.location.street}</h5>
                            <h6 class="col-1"><span class="badge bg-dark">building</span></h6> <h5
                                class="col-1 mb-3">${event.location.building}</h5>
                            <h6 class="col-1"><span class="badge bg-dark">suite</span></h6>    <h5
                                class="col-1 mb-3">${event.location.suite}</h5>
                        </div>
                    </ul>
                </div>
                </c:if>

                <c:if test="${eventsWithoutReports.contains(event) == true}">
                    <button type="reset"
                            class="btn btn-outline-warning btn-lg my-3">
                        This event has no reports yet. You can add your own.
                    </button>
                </c:if>

                <c:if test="${user.isEventParticipated(event.id) == true}">
                    <h4 class="my-3"><span class="badge badge-pill bg-success text-light">Your Report takes a part in this event</span></h4>
                </c:if>

                <form method="post" action="controller">
                    <input type="hidden" name="command" value="eventDetails">
                    <input type="hidden" name="eventId" value="${event.id}">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" class="btn btn-info btn-block">event Details...</button>
                </form>

            </div>
        </c:forEach>

    </div>

</main>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/script.jspf" %>

</body>
</html>
