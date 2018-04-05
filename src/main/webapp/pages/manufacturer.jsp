<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Manufacturer Form</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/app.css" rel="stylesheet">

</head>

<body>
<div class="container">
    <%@include file="authheader.jsp" %>
    <div class="generic-container">

        <div class="well lead">Manufacturer Form</div>
        <form:form method="POST" modelAttribute="manufacturerForm" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="name">Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="name" id="name" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="name" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-actions floatRight">
                <input type="submit" value="Save" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list_manufacturer' />">Cancel</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>