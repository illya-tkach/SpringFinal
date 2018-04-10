<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Product Form</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/app.css" rel="stylesheet">

</head>

<body>
<div class="container">
    <%@include file="authheader.jsp" %>
    <div class="generic-container">

        <div class="well lead">Product Form</div>
        <form:form method="POST" modelAttribute="productForm" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="name">Наименование</label>
                    <div class="col-md-7">
                        <form:input type="text" path="name" id="name" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="name" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="inventory">Инвентарный н-р</label>
                    <div class="col-md-7">
                        <form:input type="text" path="inventory" id="inventory" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="inventory" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="serial">Инвентарный н-р</label>
                    <div class="col-md-7">
                        <form:input type="text" path="serial" id="serial" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="serial" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="quantity">Количество</label>
                    <div class="col-md-7">
                        <form:input type="text" path="quantity" id="quantity" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="quantity" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="location">Текущее местронахождение</label>
                    <div class="col-md-7">
                        <form:input type="text" path="location" id="location" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="location" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="manufacturer">Инвентарный счет</label>
                    <div class="col-md-7">
                        <form:select path="manufacturer" size="1">
                            <form:option value="">----Select-----</form:option>
                            <c:forEach var="current" items="${listmanufacturers}">
                                <option value="${current.id}"
                                <c:if test="${current.id == productForm.manufacturer.id}"> selected </c:if>
                                >${current.name}</option>
                            </c:forEach>
                        </form:select>
                        <div class="has-error">
                            <form:errors path="manufacturer" class="help-inline"/>
                        </div>

                    </div>
                </div>
            </div>
            <div class="form-actions floatRight">
                <input type="submit" value="Сохранить" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list_products' />">Отменить</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>