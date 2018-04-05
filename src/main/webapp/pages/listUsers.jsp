<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Users List</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/app.css" rel="stylesheet"/>

</head>
<body>
<div class="container">
    <%@include file="authheader.jsp" %>
    <div class="panel panel-default">
    <div class="panel-heading"><span class="lead">List of Users </span></div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Email</th>
            <th>Role</th>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th width="100"></th>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th width="100"></th>
            </sec:authorize>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td><c:forEach items="${user.roles}" var="items">
                    <div>${items.name}</div>
                </c:forEach>
                </td>
                <sec:authorize access="hasRole('ROLE_ADMIN') ">
                    <td><a href="<c:url value='/edit-user-${user.id}' />"
                           class="btn btn-success custom-width">edit</a></td>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td><a href="<c:url value='/delete-user-${user.id}' />" class="btn btn-danger custom-width">delete</a>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<sec:authorize access="hasRole('ADMIN')">
    <div class="well">
        <a href="<c:url value='/new-user' />">Add New User</a>
    </div>
</sec:authorize>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>