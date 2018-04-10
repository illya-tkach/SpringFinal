<%@ page import="java.io.OutputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Produtcs List</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/app.css" rel="stylesheet"/>

</head>
<body>
<div class="container">
    <%@include file="authheader.jsp" %>
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Список материальных ценностей </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Наименование</th>
                <th>Инв. номер</th>
                <th>Сер. номер</th>
                <th>Кол-во</th>
                <th>Местонахождение</th>
                <th>Счет</th>
                <th width="100"></th>
                <th width="100"></th>
            </tr>
            </thead>

            <c:set var="count" value="0" />
            <tbody>
            <c:forEach items="${listproducts}" var="product" >
                <c:set var="count" value="${count+1}" />
                <tr>
                    <td><c:out value="${count}" /></td>
                    <td>${product.name}</td>
                    <td>${product.inventory}</td>
                    <td>${product.serial}</td>
                    <td>${product.quantity}</td>
                    <td>${product.location}</td>
                    <td>${product.getManufacturer().getName()}</td>
                    <td><a href="<c:url value='/edit-product-${product.id}' />"
                           class="btn btn-success custom-width">edit</a></td>
                    <td><a href="<c:url value='/delete-product-${product.id}' />" class="btn btn-danger custom-width">delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <div class="well">
        <a href="<c:url value='/new-product' />">Добавить материальную ценность</a>
    </div>


</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>