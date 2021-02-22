<%@ include file="/WEB-INF/jspf/page.jspf" %>

<html>
<c:set var="title" value="register" />
<c:set var="techPage" value="techPage" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/info.jspf" %>

<!-- Pills navs -->
<div class="container-md col-md-6 mx-auto p-5">
    <form id="newReport" action="controller" method="post"
          onsubmit="return confirm('Confirm creating Report with Topic ')">
        <input type="hidden" name="command" value="newReport"/>

        <!-- 2 column grid layout with text inputs for the first and last names -->
        <div class="row mb-4">
            <div class="col">
                <div class="form-outline">
                    <input name="topic" type="text" id="form3Example1" class="form-control" required/>
                    <label class="form-label" for="form3Example1">Topic</label>
                </div>
            </div>
        </div>

        <!-- Checkbox -->
        <div class="form-check d-flex justify-content-center mb-4">
            <input name="speaker"
                    class="form-check-input me-2 text-dark"
                    style="background: #424242; border: #424242"
                    type="checkbox"
                    value="speaker"
                    id="reportToEvent"
                    ontoggle=""
            />
            <label class="form-check-label" for="reportToEvent">
                Offer your report to the event?
            </label>
        </div>

        <div class="row mb-4">
            <div class="col">
                <div class="form-outline">
<%--                    <label class="form-label" for="select">choose an Event:</label>--%>

                    <select name="eventId" id="select" class="form-control">
                        <option >choose an Event:</option>
                        <c:forEach var="event" items="${events}">
                            <option value="${event.id}">${event.title}</option>
                        </c:forEach>
                    </select>

                </div>
            </div>
        </div>

        <!-- Submit button -->
        <button type="submit"
                class="btn btn-warning btn-block mb-4"
        >CREATE REPORT</button>
    </form>

</div>

    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    <%@ include file="/WEB-INF/jspf/script.jspf"%>

</body>
</html>
