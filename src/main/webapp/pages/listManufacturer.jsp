<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Manufacturer List</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/app.css" rel="stylesheet"/>

</head>
<body>
<div class="container">
    <%@include file="authheader.jsp" %>
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">List of Manufacturer </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th width="100"></th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listmanuf}" var="manufacturer">
                <tr>
                    <td>${manufacturer.name}</td>
                    <td><a href="<c:url value='/edit-manufacturer-${manufacturer.id}' />"
                           class="btn btn-success custom-width">edit</a></td>
                    <td><a href="<c:url value='/delete-manufacturer-${manufacturer.id}' />"
                           class="btn btn-danger custom-width">delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="well">
        <a href="<c:url value='/new-manufacturer' />">Add New Manufacturer</a>
    </div>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>