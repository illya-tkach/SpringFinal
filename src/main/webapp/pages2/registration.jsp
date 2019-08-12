<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>User Registration Form</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/app.css" rel="stylesheet">

</head>

<body>
<div class="container">
    <c:if test="${loggedinuser!= 'anonymousUser'}"><%@include file="authheader.jsp" %></c:if>
    <div class="generic-container">

    <div class="well lead">User Registration Form</div>
        <form:form method="POST" modelAttribute="userForm" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="email">Email</label>
                <div class="col-md-7">
                    <form:input type="text" path="email" id="email" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Password</label>
                <div class="col-md-7">
                    <form:input type="password" path="password" id="password" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="confirmPassword">Confirm Password</label>
                <div class="col-md-7">
                    <form:input type="password" path="confirmPassword" id="confirmPassword" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="confirmPassword" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="firstName">First Name</label>
                <div class="col-md-7">
                    <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="lastName">Last Name</label>
                <div class="col-md-7">
                    <form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="roles">Roles</label>
                <div class="col-md-7">
                        <select name="roles" size="1" >
                            <c:forEach var="current" items="${listroles}"  >
                                <option value="${current.id}"
                                        <c:if test="${current.id == userForm.getRoles().iterator().next().getId()}"> selected </c:if>
                                >${current.name}</option>
                            </c:forEach>
                        </select>
                </div>
            </div>
        </div>
            </sec:authorize>
        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list_users' />">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary btn-sm"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
</div>
</body>
</html>