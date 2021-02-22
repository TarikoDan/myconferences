<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page import="com.conference.my.model.entity.Location" %>

<html>
<c:set var="title" value="Home" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<c:set var="events" value="${applicationScope.initEvents}"/>
<c:set var="event1" value="${events[0]}"/>
<c:set var="event2" value="${events[1]}"/>
<c:set var="event3" value="${events[2]}"/>
<c:set var="registered" value="${sessionScope.registered}"/>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/info.jspf" %>

<div class="container-md col-md-5 mx-auto text-center">
    <!-- Modal -->
    <c:if test="${not empty registered}">
    <!-- Button trigger modal -->
    <button
            type="button"
            class="btn btn-success m-5"
            data-mdb-toggle="modal"
            data-mdb-target="#staticBackdrop"
    >Registration result
    </button>
    </c:if>
</div>

<!-- Carousel wrapper -->
<div
        id="carouselBasicExample"
        class="carousel slide carousel-fade"
        data-mdb-ride="carousel"
>
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-mdb-target="#carouselBasicExample" data-mdb-slide-to="0" class="active"></li>
        <li data-mdb-target="#carouselBasicExample" data-mdb-slide-to="1"></li>
        <li data-mdb-target="#carouselBasicExample" data-mdb-slide-to="2"></li>
    </ol>

    <!-- Inner -->
    <div class="carousel-inner">
        <!-- Single item -->
        <div class="carousel-item active">
            <img
                    src="https://mdbootstrap.com/img/Photos/Slides/img%20(15).jpg"
                    class="d-block w-100"
                    alt="..."
            />
            <div class="carousel-caption d-none d-md-block">
                <h5>The Great place to improve yourself</h5>
                <p>ask... decide... create... improve... persuade... offer...</p>
                <h3 class="display-3">
                    <c:out value="${event1.title}"/>
                </h3>
                <h3>
                    <c:out value="${event1.date}"/>
                </h3>
                <h5>${event1.location.country}. --${event1.location.city}--</h5>

                <form method="post" action="controller">
                    <input type="hidden" name="command" value="eventDetails">
                    <input type="hidden" name="eventId" value="${event1.id}">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" class="btn btn-outline-light btn-lg">event Details...</button>
                </form>

            </div>
        </div>

        <!-- Single item -->
        <div class="carousel-item">
            <img
                    src="https://mdbootstrap.com/img/Photos/Slides/img%20(22).jpg"
                    class="d-block w-100"
                    alt="..."
            />
            <div class="carousel-caption d-none d-md-block">
                <h5>The Great place to improve yourself</h5>
                <p>ask... decide... create... improve... persuade... offer...</p>
                <h3 class="display-3">
                    <c:out value="${event2.title}"/>
                </h3>
                <h3>
                    <c:out value="${event2.date}"/>
                </h3>
                <h5>${event2.location.country}. --${event2.location.city}--</h5>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="eventDetails">
                    <input type="hidden" name="eventId" value="${event2.id}">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" class="btn btn-outline-light btn-lg">event Details...</button>
                </form>

            </div>
        </div>

        <!-- Single item -->
        <div class="carousel-item">
            <img
                    src="https://mdbootstrap.com/img/Photos/Slides/img%20(23).jpg"
                    class="d-block w-100"
                    alt="..."
            />
            <div class="carousel-caption d-none d-md-block">
                <h5>The Great place to improve yourself</h5>
                <p>ask... decide... create... improve... persuade... offer...</p>
                <h3 class="display-3">
                    <c:out value="${event3.title}"/>
                </h3>
                <h3>
                    <c:out value="${event3.date}"/>
                </h3>
                <h5>${event3.location.country}. --${event3.location.city}--</h5>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="eventDetails">
                    <input type="hidden" name="eventId" value="${event3.id}">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" class="btn btn-outline-light btn-lg">event Details...</button>
                </form>

            </div>
        </div>
    </div>
    <!-- Inner -->

    <!-- Controls -->
    <a
            class="carousel-control-prev"
            href="#carouselBasicExample"
            role="button"
            data-mdb-slide="prev"
    >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </a>
    <a
            class="carousel-control-next"
            href="#carouselBasicExample"
            role="button"
            data-mdb-slide="next"
    >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </a>
</div>
<!-- Carousel wrapper -->

 <!-- Registration result -->
<div
        class="modal fade"
        id="staticBackdrop"
        data-mdb-backdrop="static"
        data-mdb-keyboard="false"
        tabindex="-1"
        aria-labelledby="staticBackdropLabel"
        aria-hidden="true"
>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                <button
                        type="button"
                        class="btn-close"
                        data-mdb-dismiss="modal"
                        aria-label="Close"
                ></button>
            </div>
            <div class="modal-body">...
                Registration success!
                Name:<c:out value="${registered.name}"/>
                Email:<c:out value="${registered.email}"/>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary"
                        data-mdb-dismiss="modal"
                        onclick="${sessionScope.remove("registered")}"
                > Close
                </button>
            </div>
        </div>
    </div>

</div>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/script.jspf"%>
</body>
</html>
