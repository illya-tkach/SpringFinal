<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Products List</title>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
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
            <li class="nav-item">
                <a class="nav-link" href="/employeeTask"><fmt:message key="menu.booking"></fmt:message><span class="sr-only">(current)</span></a>
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
            <li class="nav-item mr-2">
                <span style="color:red">[ ${loginedUser.userName} ]</span>
            </li>
            <li class="nav-item mr-2">
                <a class="nav-link" href="/logout"><fmt:message key="menu.logout" /></a>
            </li>
        </ul>

    </div>
</nav>
<div class="container d-flex justify-content-center" >
    <div class="card border-dark mb-3" style="max-width: 18rem;">
        <div class="card-header">Your email: ${client.email}</div>
        <div class="card-body text-dark">
            <h5 class="card-title">Your name: ${client.firstName} ${client.lastName}</h5>
            <p class="card-text">Current balance: ${client.balance} <span>&#8372;</span></p>
        </div>
        <button type="button" onclick="showAddMoney();return false;" class="btn btn-secondary">Add balance</button>
    </div>
</div>
<script type="text/javascript">
        function showAddMoney() {
            $("#addMoneyModal").modal("show");
        }
</script>
</body>
<div class="modal fade" id="addMoneyModal" tabindex="-1" role="dialog" aria-labelledby="addMoneyModalTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addMoneyModalTitle"><fmt:message key="barberModal.chooseEmployee"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container ">
                    <form action="/addMoney" method="post">
                        <div class="form-row align-items-center">
                            <div class="col-sm-3 my-1">
                                <label class="sr-only" for="inlineFormInputName">Enter money</label>
                                <input type="text" class="form-control" id="inlineFormInputName" name="money">
                            </div>
                            <div class="col-auto my-1">
                                <button type="submit" class="btn btn-primary">Add to balance</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <%--                    <button id="clickme" type="button" class="btn btn-primary">Save changes</button>--%>
                </div>
            </div>
        </div>
    </div>
</div>
</html>
