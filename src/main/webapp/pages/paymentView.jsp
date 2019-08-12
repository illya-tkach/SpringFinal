<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Products List</title>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>

    <style type="text/css">
        body{
            height: 200px; /* Высота блока */
            /*border: 2px solid #000; !* Параметры рамки *!*/
            background: url("${contextPath}/resources/images/barber.jpeg") 100% 100% no-repeat; /* Добавляем фон */
            background-size: cover; /* Масштабируем фон */
        }
    </style>

    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

    <script defer src="${contextPath}/resources/fontawesome-free-5.6.1-web/js/all.min.js"></script>
</head>
<body>
<nav id="navbar" class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#"><i class="fab fa-monero"></i><fmt:message key="name" /></a>
    <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/"><fmt:message key="menu.main" /><span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <ul class="navbar-nav ">
            <li class="nav-item">
                <span class="navbar-text"><fmt:message key="lang.change" /></span>:
                <select id="locales">
                    <option value=""></option>
                    <option value="ua"><fmt:message key="lang.ua" /></option>
                    <option value="en"><fmt:message key="lang.en" /></option>
                </select>
            </li>
            <li class="nav-item mr-2">
                <span style="color:red">[ ${loginedUser.userName} ]</span>
            </li>
            <c:if test="${loginedUser!= null}">
                <li class="nav-item mr-2">
                    <a class="nav-link" href="/logout"><fmt:message key="menu.logout" /></a>
                </li>
            </c:if>
        </ul>

    </div>
</nav>

<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('/?sessionLocale=' + selectedOption);
            }
        });
    });
</script>
<div class="container">
    <div class="row justify-content-center">
        <span style="color:red;">${error}</span>
    </div>
    <div class="row justify-content-center">

        <div class="col-6">
            <fmt:message key="menu.payment.barber"/>: ${param.barberName}
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-6">
            <fmt:message key="menu.payment.service"/>: ${param.serviceName}
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-6">
            <fmt:message key="menu.payment.cost"/>: ${cost}
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-6">
            <fmt:message key="menu.payment.date"/>: ${param.dateAndTime}
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-6">
            <form action="/payment" method="post" id="form-id">
                <input type="hidden" id="barberId" name="barberId" value="${param.barberId}">
                <input type="hidden" id="serviceId" name="serviceId" value="${param.serviceId}">
                <input type="hidden" id="dateAndTime" name="dateAndTime" value="${param.dateAndTime}">
                <input type="hidden" id="barberName" name="barberName" value="${param.barberName}">
                <input type="hidden" id="serviceName" name="serviceName" value="${param.serviceName}">
                <input type="hidden" id="cost" name="cost" value="${cost}">
                <button id="your-id" form="form-id" class="btn btn-secondary btn-lg mt-1" value="Submit"><fmt:message key="menu.payment"/></button>
            </form>
        </div>
    </div>
</div>
<script>
    var form = document.getElementById("form-id");
    document.getElementById("your-id").addEventListener("click", function () {
        form.submit();
    });
</script>

</body>
</html>
