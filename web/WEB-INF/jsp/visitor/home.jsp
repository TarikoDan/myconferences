<%@ include file="/WEB-INF/jspf/page.jspf"%>
<html>

<c:set var="title" value="conferenses" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<%--Visitor Home Page--%>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%--<jsp:include page="/WEB-INF/jspf/header.jspf" />--%>

<main>
    <%--    <c:out value="<%= new java.util.Date() %>"/>--%>
<%--    <c:set var="now" value="<%= java.time.LocalDate.now() %>"/>--%>
<%--    <c:out value="Today is: ${now}"/>--%>
    <%--    <jsp:useBean id="now" class="java.util.Date"  />--%>
    <%--    <fmt:formatDate value="${now}"  pattern="yyyy-MM-dd"/>--%>

    <%--    <c:set var="events" value="${applicationScope.get('init')}"/>--%>
    <c:set var="events" value="${applicationScope.initEvents}"/>
    <c:set var="registered" value="${sessionScope.registered}"/>

    <div class="container-md col-md-8 mx-auto text-center">
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

        <c:forEach items="${events}" var="event">
            <h4 class="text-black-50"> <c:out value="${event.title}"/> </h4>
            <h5> <c:out value="${event.date}"/> </h5>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="eventDetails">
                    <input type="hidden" name="eventId" value="${event.id}">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" class="btn btn-dark">event Details...</button>
                </form>
            <br>
        </c:forEach>

    </div>


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

</main>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%@ include file="/WEB-INF/jspf/script.jspf"%>
</body>
</html>
