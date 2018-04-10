<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>First page</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


</head>

<body>


<div class="container">
    <%@include file="authheader.jsp" %>
    <div class="panel panel-default">
        <div class="panel-heading">
            <table class="table table-hover">
                <tr>
                    <td>
                        <a href="<c:url value='/list_users' />">Список всех пользователей</a><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="<c:url value='/list_manufacturer' />">Список всех счетов</a>
                    </td>
                </tr>

                <tr>
                    <td>
                        <a href="<c:url value='/list_products' />">Список всех материальных ценностей</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>