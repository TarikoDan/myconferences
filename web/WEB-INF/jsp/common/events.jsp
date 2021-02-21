<%@ include file="/WEB-INF/jspf/page.jspf"%>
<%@ page import="com.conference.my.model.entity.Location" %>

<html>
<c:set var="title" value="conferenses" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<%--Events Page for unRegistered Users--%>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/info.jspf" %>

<main>
    <c:set var="events" value="${applicationScope.initEvents}"/>

    <div class="container-md col-md-8 mx-auto text-center">

        <c:forEach items="${events}" var="event">

            <div class="card bg-dark shadow-3-strong border border-dark my-4 text-white">
                <img
                        src="https://picsum.photos/id/${event.id}/720/300"
                        class="card-img"
                        alt="cardBackground"
                />
                <div class="mask" style="background-color: rgba(0, 0, 0, 0.6)"></div>
                <div class="card-img-overlay py-2">
                    <h3 class="card-title">${event.title}</h3>
                    <p class="card-text"></p>
                    <h5> <c:out value="${event.date}"/> </h5>
                    <h5>${event.location.zipCode}   ${event.location.country},  ${event.location.city}</h5>
                    <h6>${event.location.street} str.,   ${event.location.building} / ${event.location.suite}</h6>
                    <form method="post" action="controller" class="mt-5">
                        <input type="hidden" name="command" value="eventDetails">
                        <input type="hidden" name="eventId" value="${event.id}">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit" class="btn btn-outline-light btn-lg">event Details...</button>
                    </form>
                </div>
            </div>

        </c:forEach>
    </div>

</main>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/script.jspf"%>
</body>
</html>
