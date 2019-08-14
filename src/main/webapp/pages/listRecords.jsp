<%@ page import="java.io.OutputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Users List</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>

</head>
<body>
<nav id="navbar" class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#"><i class="fab fa-monero"></i><fmt:message key="name"/></a>
    <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/"><fmt:message key="menu.main" /><span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <ul class="navbar-nav ">
            <li class="nav-item mr-2">
                <span class="navbar-text"><fmt:message key="lang.change" /></span>:
                <select id="locales">
                    <option value=""></option>
                    <option value="ua"><fmt:message key="lang.ua" /></option>
                    <option value="en"><fmt:message key="lang.en" /></option>
                </select>
            </li>
            <li class="nav-item">
                <span class="navbar-text">
                Login as:
                </span>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <strong><security:authentication property="principal.username"/></strong>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value="/logout"/>">Выйти</a>
                </div>
            </li>
        </ul>

    </div>
</nav>
<div class="container d-flex justify-content-center">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead"><fmt:message key="recordPage.tableTitle" /></span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="recordPage.table.barber" /></th>
                <th><fmt:message key="recordPage.table.serviceType" /></th>
                <th><fmt:message key="recordPage.table.clientName" /></th>
                <th><fmt:message key="recordPage.table.clientMail" /></th>
                <th><fmt:message key="recordPage.table.date" /></th>
                <th><fmt:message key="recordPage.table.time" /></th>
                <th><fmt:message key="recordPage.table.status" /></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th width="100"></th>
                <th width="100"></th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${recordList}" var="record">
                <tr>
                    <td>${record.barber.firstName} ${record.barber.lastName}</td>
                    <c:choose>
                        <c:when test= "${record.service != null} ">
                            <td>${record.service.serviceName}</td>
                        </c:when>
                        <c:otherwise>
                            <td>-</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test= "${record.client != null}">
                            <td>${record.client.firstName} ${record.client.lastName}</td>
                            <td>${record.client.email}</td>
                        </c:when>
                        <c:otherwise>
                            <td>-</td>
                            <td>-</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${record.localDate}</td>
                    <td>${record.localTime}</td>
                    <td>${record.status}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td><a href="<c:url value='/served-${record.id}' />" class="btn btn-secondary custom-width"><fmt:message key="recordPage.button.served" /></a></td>
                    <td><a href="<c:url value='/remove-${record.id}' />" class="btn btn-danger custom-width"><fmt:message key="recordPage.button.delete" /></a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<sec:authorize access="hasRole('ADMIN')">
<div align="center" class="well">
    <a class="btn btn-primary" href="<c:url value='/newRecord' />"><fmt:message key="recordPage.button.addNew" /></a>
</div>
<%--    <button onclick="AddRecordModal();return false;" type="button" class="btn btn-outline-primary mb-3">Add new Record</button>--%>
</sec:authorize>

<script>

</script>


<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('/recordList?lang=' + selectedOption);
            }
        });
    });
</script>
<!-- /container -->
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
<%--<script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
</body>
</html>