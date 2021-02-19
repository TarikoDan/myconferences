<%@ include file="/WEB-INF/jspf/page.jspf" %>

<html>

<c:set var="title" value="register" />
<c:set var="background" value="${pageContext.request.contextPath}/images/default.jpg" />
<c:set var="height" value="200px" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<!-- Pills navs -->
<div class="container-md col-md-6 mx-auto p-5">
    <form id="updateUser" action="controller" method="post">
        <input type="hidden" name="command" value="updateUser"/>

        <!-- 2 column grid layout with text inputs for the first and last names -->
        <div class="row mb-4">
            <div class="col">
                <div class="form-outline">
                    <input name="name" value="${user.name}" type="text" id="form3Example1" class="form-control" required/>
                    <label class="form-label" for="form3Example1">First name</label>
                </div>
            </div>
            <div class="col">
                <div class="form-outline">
                    <input name="lastname" value="${user.lastname}" type="text" id="form3Example2" class="form-control" />
                    <label class="form-label" for="form3Example2">Last name</label>
                </div>
            </div>
        </div>

        <!-- Email input -->
        <div class="form-outline mb-4">
            <input name="email" value="${user.email}" type="email" id="form3Example3" class="form-control" required/>
            <label class="form-label" for="form3Example3">Email address</label>
        </div>

        <!-- Password input -->
        <div class="form-outline mb-4">
            <input name="password" value="${user.password}"  type="password" id="form3Example4" class="form-control" required/>
            <label class="form-label" for="form3Example4">Password</label>
        </div>

        <!-- Checkbox -->
        <div class="form-check d-flex justify-content-center mb-4">
            <input name="speaker"
                    class="form-check-input me-2 text-dark"
                    style="background: #424242; border: #424242"
                    type="checkbox"
                    value="speaker"
                    id="form2Example3"
            />
            <label class="form-check-label" for="form2Example3">
                Want to be a SPEAKER?
            </label>
        </div>

        <!-- Submit button -->
        <button type="submit"
                class="btn btn-warning btn-block mb-4"
        >UPDATE</button>
    </form>

</div>

    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
    <%@ include file="/WEB-INF/jspf/script.jspf"%>

</body>
</html>
