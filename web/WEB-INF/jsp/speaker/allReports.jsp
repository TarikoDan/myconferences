<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page import="com.conference.my.model.entity.Location" %>

<html>
<c:set var="title" value="conferenses"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ include file="/WEB-INF/jspf/info.jspf" %>

<main class="position-relative">
    <h6>REPORTs SPEAKER Page</h6>
    <div class="d-flex justify-content-end">
        <form method="post" action="controller" class="my-0 p-2">
            <input type="hidden" name="command" value="navReports"/>
            <input type="hidden" name="sortReports" value="sortReportsByTopic"/>
            <button type="submit" class="btn btn-primary my-auto btn-sm btn-rounded">sort by TOPIC</button>
        </form>
        <form method="post" action="controller" class="my-0 p-2">
            <input type="hidden" name="command" value="navReports"/>
            <input type="hidden" name="sortReports" value="getAllMine"/>
            <button type="submit" class="btn btn-primary my-auto btn-sm btn-rounded">get MY Reports</button>
        </form>
        <form method="post" action="controller" class="my-0 p-2">
            <input type="hidden" name="command" value="navReports"/>
            <input type="hidden" name="sortReports" value="withoutSpeakers"/>
            <button type="submit" class="btn btn-primary my-auto btn-sm btn-rounded">withoutSpeakers</button>
        </form>

    </div>

    <div class="container-md col-md-8 mx-auto text-center">
        <c:forEach items="${reports}" var="report">
            <div style="background: blanchedalmond; border: 3px solid saddlebrown; border-radius: 5px; margin: 10px"
            <c:if test="${user.isReportMine(report.id) == true}">
                class="bg-light shadow-2-strong m-3"
            </c:if>
            >
                <h2><c:out value="${report.topic}"/></h2>
<%--                <h4><c:out value="${event.date}"/></h4>--%>
<%--                <h6><c:out value="${event.location}"/></h6>--%>


                <c:if test="${reportsWithoutSpeakers.contains(report) == true}">
                    <button type="reset"
                            class="btn btn-outline-warning btn-lg my-3">
                        This report has no Speaker yet. You can become One.
                    </button>
                </c:if>

                <c:if test="${user.isReportMine(report.id) == true}">
                    <h4 class="my-3"><span class="badge badge-pill bg-success text-light">you are the author of this report</span></h4>
                </c:if>

                <form method="post" action="controller">
                    <input type="hidden" name="command" value="reportDetails">
                    <input type="hidden" name="eventId" value="${report.id}">
                    <button type="submit" class="btn btn-info btn-block">report Details...</button>
                </form>

            </div>
        </c:forEach>

    </div>

<div class="position-absolute top-50 end-0 mx-4"
<%--     style="background: #60d060; width: 50px; height: 50px"--%>
>
    <form method="post" action="controller" >
        <input type="hidden" name="command" value="createOwnReport"/>
        <button type="submit" class="btn btn-rounded">
            <img width="50px" src="${pageContext.request.contextPath}/images/plus.png" alt="logo">
        </button>
    </form>
</div>
</main>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%@ include file="/WEB-INF/jspf/script.jspf" %>

</body>
</html>
