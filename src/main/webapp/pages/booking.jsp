<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Booking</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


</head>

<body>

<div class="container">

    <form method="POST" action="${contextPath}/bookNow" class="form-signin">
        <h2 class="form-heading">Registration for visit Barbershop</h2>
        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="phoneNumber" type="text" class="form-control" placeholder="Phone Number"/>
            <input name="email" type="text" class="form-control" placeholder="Email"/>
            <span>${error}</span>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Visit us</button>
        </div>

    </form>

</div>

</body>
</html>