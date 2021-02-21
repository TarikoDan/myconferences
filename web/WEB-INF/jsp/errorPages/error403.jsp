<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/403.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
            rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.css"
            rel="stylesheet"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

</head>
<body>
<div class="ghost">

    <div class="ghost--main">
        <div class="text-center mt-5 col-md-5 mx-auto">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="loginPage"/>
                <button class="btn btn-warning mb-4">
                    BACK to LogIN page
                </button>
            </form>

            <a class="btn btn-success "
               href="${pageContext.request.contextPath}">
                BACK to home page
            </a>
        </div>
        <h4 class="text-warning text-capitalize text-center" style="margin-top: 500px">${errorMessage}</h4>
    </div>

</div>

<h1 class="police-tape police-tape--1">
    &nbsp;&nbsp;&nbsp;&nbsp;Error: 403&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Error: 403&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Error: 403&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Error: 403&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Error: 403&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Error: 403&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Error: 403
</h1>
<h1 class="police-tape police-tape--2">Forbidden&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Forbidden&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Forbidden&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Forbidden&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Forbidden&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Forbidden&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>


</body>
</html>
