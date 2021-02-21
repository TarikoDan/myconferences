<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/409.css">
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
    <div class="error">
        <h1>409</h1>
        <h2>error</h2>
        <p>Ohh, SORRY, but this email <mark>${wrongEmail}</mark> is already in use!</p>
        <p>Try another One, or LogIn.</p>
        <form method="post" action="controller" class="m-4">
            <input type="hidden" name="command" value="loginPage"/>
            <button type="submit" class="btn btn-dark">BACK</button>
        </form>
    </div>
    <div class="stack-container">
        <div class="card-container">
            <div class="perspec" style="--spreaddist: 125px; --scaledist: .75; --vertdist: -25px;">
                <div class="card">
                    <div class="writing">
                        <div class="topbar">
                            <div class="red"></div>
                            <div class="yellow"></div>
                            <div class="green"></div>
                        </div>
                        <div class="code">
                            <ul>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-container">
            <div class="perspec" style="--spreaddist: 100px; --scaledist: .8; --vertdist: -20px;">
                <div class="card">
                    <div class="writing">
                        <div class="topbar">
                            <div class="red"></div>
                            <div class="yellow"></div>
                            <div class="green"></div>
                        </div>
                        <div class="code">
                            <ul>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-container">
            <div class="perspec" style="--spreaddist:75px; --scaledist: .85; --vertdist: -15px;">
                <div class="card">
                    <div class="writing">
                        <div class="topbar">
                            <div class="red"></div>
                            <div class="yellow"></div>
                            <div class="green"></div>
                        </div>
                        <div class="code">
                            <ul>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-container">
            <div class="perspec" style="--spreaddist: 50px; --scaledist: .9; --vertdist: -10px;">
                <div class="card">
                    <div class="writing">
                        <div class="topbar">
                            <div class="red"></div>
                            <div class="yellow"></div>
                            <div class="green"></div>
                        </div>
                        <div class="code">
                            <ul>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-container">
            <div class="perspec" style="--spreaddist: 25px; --scaledist: .95; --vertdist: -5px;">
                <div class="card">
                    <div class="writing">
                        <div class="topbar">
                            <div class="red"></div>
                            <div class="yellow"></div>
                            <div class="green"></div>
                        </div>
                        <div class="code">
                            <ul>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-container">
            <div class="perspec" style="--spreaddist: 0px; --scaledist: 1; --vertdist: 0px;">
                <div class="card">
                    <div class="writing">
                        <div class="topbar">
                            <div class="red"></div>
                            <div class="yellow"></div>
                            <div class="green"></div>
                        </div>
                        <div class="code">
                            <ul>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="${pageContext.request.contextPath}/css/409.js"></script>
</body>
</html>
