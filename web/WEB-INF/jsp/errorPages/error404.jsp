<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/404.css">
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

<div class="container">
    <div class="row">
        <div class="xs-12 md-6 mx-auto">
            <div id="countUp">
                <div class="number" data-count="404">404</div>
                <div class="text">Page not found</div>
                <div class="text">This may not mean anything.</div>
                <div class="text">I'm probably working on something that has blown up.</div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/css/404.js"></script>
</body>
</html>
