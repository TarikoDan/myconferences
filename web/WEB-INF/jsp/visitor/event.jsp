<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page import="com.conference.my.model.entity.Location" %>

<html>

<c:set var="title" value="event - ${event.title}"/>
<c:set var="background" value="'https://mdbootstrap.com/img/new/slides/040.jpg'" />
<c:set var="height" value="200px" />

<c:set var="now" value="<%= java.time.LocalDate.now() %>"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container-fluid mx-auto p-5 ">

    <div class="card text-center ">
        <div class="card-header">
            <ul class="nav nav-tabs card-header-tabs">
                <li><button class="btn btn-dark mx-3" onclick="reports()">reports</button></li>
                <li><button class="btn btn-dark mx-3" onclick="speakers()">speakers</button></li>
                <li><button class="btn btn-dark mx-3" onclick="locations()">locations</button></li>
            </ul>
        </div>
        <div class="card-body">
            <h1 class="card-title"><c:out value="${event.title}"/></h1>
            <h5 >This event is ongoing right now</h5>
            <h3><c:out value="${event.date}"/></h3>

            <p class="card-text">
                With supporting text below as a natural lead-in to additional content.
            </p>
            <div id="reports" >
                <h1>reports</h1>
                <c:forEach items="${eventReports}" var="report">
                    <h2>Topic:  <c:out value="${report.topic}"/></h2>
                    <h3>Speaker: <c:out value="${report.speaker.name}"/></h3>
                </c:forEach>
                <hr>
            </div>

            <div id="speakers" hidden>
                <h2>Speakers</h2>
                <c:forEach items="${eventSpeakers}" var="speaker">
                    <h3>Name:  <c:out value="${speaker.name}"/></h3>
                    <h4>Email: <c:out value="${speaker.email}"/></h4>
                </c:forEach>
                <hr>
            </div>

            <div id="location" hidden>
                <h1>location</h1>
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
        </div>

    </div>

    <div class="text-center">
        <form action="controller" method="post" class="mt-5">
            <input type="hidden" name="command" value="navEvents"/>
            <button type="submit" class="btn btn-info">BACK to EVENTS page</button>
        </form>
    </div>

</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

<script>
    function reports() {
        document.getElementById("reports").hidden = false;
        document.getElementById("speakers").hidden = true;
        document.getElementById("location").hidden = true;
    }

    function speakers() {
        document.getElementById("reports").hidden = true;
        document.getElementById("speakers").hidden = false;
        document.getElementById("location").hidden = true;
    }

    function locations() {
        document.getElementById("reports").hidden = true;
        document.getElementById("speakers").hidden = true;
        document.getElementById("location").hidden = false;
    }

</script>
<%@ include file="/WEB-INF/jspf/script.jspf" %>

</body>
</html>
