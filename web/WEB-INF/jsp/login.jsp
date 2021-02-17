<%@ include file="/WEB-INF/jspf/page.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container col-md-5 mx-auto">
    <form action="controller" method="post">

        <input type="hidden" name="command" value="login"/>

        <!-- Email input -->
        <div class="form-outline mb-4 " >
            <input name="email" type="email" id="form2Example1" class="form-control" />
            <label class="form-label" for="form2Example1">Email address</label>
        </div>

        <!-- Password input -->
        <div class="form-outline mb-4">
            <input name="password" type="password" id="form2Example2" class="form-control" />
            <label class="form-label" for="form2Example2">Password</label>
        </div>

        <!-- 2 column grid layout for inline styling -->
        <div class="row mb-4">
            <div class="col-md-3 d-flex justify-content-center">
                <!-- Checkbox -->
                <div class="form-check">
                    <input
                            style="background: #424242; border: #424242"
                            class="form-check-input text-dark"
                            type="checkbox"
                            value=""
                            id="form2Example3"
                            checked
                    />
                    <label class="form-check-label" for="form2Example3"> Remember me </label>
                </div>
            </div>

            <div class="col">
                <!-- Simple link -->
                <a href="#" class="link-dark">Forgot password?</a>
            </div>
        </div>

        <!-- Submit button -->
        <button type="submit" class="btn btn-dark btn-block mb-4">Sign in</button>
    </form>

        <!-- Register buttons -->
        <div class="text-center">
            <p>Not a member?
                <form id="reg" method="post" action="controller">
                <input type="hidden" name="command" value="register">
                <button type="submit" class="link-black">Register!</button>
                </form>
            </p>

            <p>or sign up with:</p>
            <a class="btn btn-dark btn-floating mx-1"
               role="button"
                target="_blank"
               href="https://www.facebook.com/">
            <i class="fab fa-facebook-f"></i>
            </a>

            <a class="btn btn-dark btn-floating mx-1"
               role="button"
                target="_blank"
                href="https://accounts.google.com/">
            <i class="fab fa-google"></i>
            </a>

            <a class="btn btn-dark btn-floating mx-1"
               href="https://twitter.com/"
               target="_blank"
               role="button"
            >
                <i class="fab fa-twitter"></i>
            </a>

            <a class="btn btn-dark btn-floating mx-1"
               href= 'https://github.com'
               target="_blank"
               role="button"
            >
                <i class="fab fa-github"></i>
            </a>
        </div>
</div>


    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    <%@ include file="/WEB-INF/jspf/script.jspf"%>

</body>
</html>
