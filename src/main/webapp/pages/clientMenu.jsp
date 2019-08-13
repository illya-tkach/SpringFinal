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
    <title>Products List</title>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <%--    <script type="text/javascript" src="${contextPath}/resources/jQuery-3.2.1/jquery-3.2.1.js"></script>--%>
    <%--    <script type="text/javascript" src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
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
    <a class="navbar-brand" href="#"><i class="fab fa-monero"></i><fmt:message key="name"/></a>
    <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/"><fmt:message key="menu.main" /><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/personal"><fmt:message key="menu.personal"/><span class="sr-only">(current)</span></a>
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
<div id="page-wrapper" class="container d-flex justify-content-center">
    <div class="col-12">
        <div class="container d-flex justify-content-center" >
            <div class="col-6">
                <div class="list-group mt-2 mb-2">
                    <a href="#" onclick="showBarberModal();return false;" class="list-group-item list-group-item-action flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1"><fmt:message key="employee" /></h5>
                            <small>
                                <button type="button" class="close" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </small>
                        </div>
                        <small id="smallBarberNameText" class="text-muted"></small>
                    </a>
                    <a href="#" onclick="showServiceModal();return false;" class="list-group-item list-group-item-action flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1"><fmt:message key="service" /></h5>
                            <small class="text-muted">
                                <button type="button" class="close" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </small>
                        </div>
                        <small id="smallServiceTypeText" class="text-muted"></small>
                    </a>
                    <a href="#" onclick="showDateModal();return false;" class="list-group-item list-group-item-action flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1"><fmt:message key="dateAndTime" /></h5>
                            <small class="text-muted">
                                <button type="button" class="close" aria-label="Close" onClick="clearFields()">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </small>
                        </div>
                        <small id="smallDateAndTimeText" class="text-muted"></small>
                        <script type="text/javascript">
                            function clearFields() {
                                $("#textfield1").text("");
                            }
                        </script>

                        <script type="text/javascript">
                            function showBarberModal() {
                                $.ajax({
                                    url: setBarberUrl(),
                                    dataType: 'json',
                                    success: function (data) {
                                        var select = $('#prodResponser').empty();
                                        $.each(data, function (i, item) {
                                            select.append('<li class="media mb-3" value='+item.id+' onclick="selectBarber(this);return false;" style="cursor: pointer;" >'
                                                + '<img src="'+item.photoUrl+'" class="mr-3" width="100" height="100" >'
                                                +'<div class="media-body"><h5 class="mt-0 mb-1">'+item.lastName+' '+item.firstName+'</h5>'
                                                + item.barberLevel.levelName
                                                + '</div>'
                                                + '</li>');

                                        });
                                    }
                                });
                                $("#exampleModalBarber").modal("show");
                            }
                            function setBarberUrl() {
                                if ($("#smallDateAndTimeText").text().length == 0 && $("#smallServiceTypeText").text().length == 0){
                                    return "/barbersAll";
                                } else if ($("#smallDateAndTimeText").text().length == 0) {
                                    return "/barbersByService-" + $("#serviceId").val();
                                } else if ($("#smallServiceTypeText").text().length == 0) {
                                    let arrayDate = sessionStorage.getItem('selectedDate').split("/");
                                    let resultDate = arrayDate[0] + "-" + arrayDate[1] + "-" + arrayDate[2];
                                    return "/barbersByCalendar-" + resultDate + '_' + sessionStorage.getItem('selectedTime');
                                }
                            }
                            function selectBarber(obj) {
                                var barberNameText = $(obj).children('div').children('h5').text();
                                var idBarber = $(obj).val();
                                $( "#barberId" ).val(  idBarber );
                                $( "#barberName" ).val(  barberNameText );
                                $("#smallBarberNameText").text( barberNameText );

                                if ($("#smallDateAndTimeText").text().length != 0 && $("#smallServiceTypeText").text().length != 0){
                                    $('#your-id').attr("disabled", false);
                                }

                                $("#exampleModalBarber").modal("hide");
                            }
                        </script>
                        <script type="text/javascript">
                            function showDateModal() {
                                // var availableDates = ["17-7-2019","14-7-2019","11-7-2019"];
                                var availableDates = [];
                                $.ajax({
                                    url: '/dateAll',
                                    dataType: 'json',
                                    success: function (data) {
                                        $.each(data, function (i, item) {
                                            var date = item.dayOfMonth + "-" + item.monthValue + "-" + item.year;
                                            console.log(date);
                                            availableDates.push(date);
                                        });
                                    }
                                });
                                function available(date) {
                                    dmy = date.getDate() + "-" + (date.getMonth()+1) + "-" + date.getFullYear();
                                    if ($.inArray(dmy, availableDates) != -1) {
                                        return [true, "","Available"];
                                    } else {
                                        return [false,"","unAvailable"];
                                    }
                                }

                                $('#date').datepicker({
                                    onSelect: function(date) {
                                        getTimeAndPopulate(date);
                                        sessionStorage.setItem('selectedDate', date)
                                        $("#exampleModalCenter").modal("hide");
                                        $("#exampleModalTime").modal("show");
                                    },
                                    beforeShowDay: available
                                });
                                $("#exampleModalCenter").modal("show");
                            }
                        </script>
                        <script>
                            function getTimeAndPopulate(date) {
                                var array = date.split("/");
                                var resultString = array[1] + "-" + array[0] + "-" + array[2];
                                $.ajax({
                                    type: 'post',
                                    url: '/timeAll-' + resultString,
                                    success: function (data) {
                                        var select = $('#timeRecordList').empty();
                                        $.each(data, function (i, item) {
                                            select.append('<div class="col-sm">' +
                                                '<button onclick="selectTime(this);return false;" type="button" class="btn btn-outline-primary mb-3">'+item.hour+':'+item.minute+'</button>' +
                                                '</div>');

                                        })
                                    },
                                    error: function () {
                                        alert('error in AJAX getAllTimeByDate');
                                    }
                                });
                            }
                        </script>
                        <script>
                            function selectTime(obj) {
                                var time = $(obj).text();
                                sessionStorage.setItem('selectedTime', time);
                                $("#dateAndTime").val( sessionStorage.getItem('selectedDate') + ' ' + time );
                                $("#smallDateAndTimeText").text( sessionStorage.getItem('selectedDate') + ' ' + time );
                                if ($("#smallBarberNameText").text().length != 0 && $("#smallServiceTypeText").text().length != 0){
                                    $('#your-id').attr("disabled", false);
                                }
                                $("#exampleModalTime").modal("hide");
                            }
                        </script>
                        <script>
                            function showServiceModal() {
                                $.ajax({
                                    url: '/servicesAll',
                                    dataType: 'json',
                                    success: function (data) {
                                        var select = $('#servicesList').empty();
                                        $.each(data, function (i, item) {
                                            select.append('<li onclick="selectService(this);return false;"  value='+item.id+' class="list-group-item list-group-item-action mb-3" style="cursor: pointer;" >'
                                                +'<div class="d-flex w-100 justify-content-between">' +
                                                '<h5 class="mb-1" >'+item.serviceName+'</h5>' +
                                                '<small>'+item.serviceCost+' '+'<span>&#8372;</span>' +
                                                '</small>' +
                                                '</div>' +
                                                '</li>');
                                        });
                                    }
                                });
                                $("#exampleModalService").modal("show");
                            }
                            function selectService(obj) {
                                var serviceText = $(obj).children().children('h5').text();
                                var idService = $(obj).val();
                                $("#serviceId").val( idService );
                                $("#serviceName").val( serviceText );
                                $("#smallServiceTypeText").text( serviceText );
                                if ($("#smallBarberNameText").text().length != 0 && $("#smallDateAndTimeText").text().length != 0){
                                    $('#your-id').attr("disabled", false);
                                }
                                $("#exampleModalService").modal("hide");
                            }
                        </script>
                    </a>
                    <form action="/booking" method="post" id="form-id">
                        <input type="hidden" id="barberId" name="barberId" value="">
                        <input type="hidden" id="barberName" name="barberName" value="">
                        <input type="hidden" id="serviceName" name="serviceName" value="">
                        <input type="hidden" id="serviceId" name="serviceId" value="">
                        <input type="hidden" id="dateAndTime" name="dateAndTime" value="">
                        <button id="your-id" form="form-id" class="btn btn-secondary btn-lg btn-block mt-1" value="Submit" disabled><fmt:message key="menu.book"/></button>
                    </form>
                    <script>
                        var form = document.getElementById("form-id");
                        document.getElementById("your-id").addEventListener("click", function () {
                            form.submit();
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('/clientMenu?lang=' + selectedOption);
            }
        });
    });
</script>

</body>
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle"><fmt:message key="dateAndTimeModal.chooseDate"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container ">
                    <div class="d-flex justify-content-center">
                        <span name="date" id="date"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <%--                    <button id="clickme" type="button" class="btn btn-primary">Save changes</button>--%>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModalTime" tabindex="-1" role="dialog" aria-labelledby="exampleModalTimeTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalTimeTitle"><fmt:message key="dateAndTimeModal.chooseTime"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container ">
                    <div id="timeRecordList" class="row">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <%--                    <button id="clickme" type="button" class="btn btn-primary">Save changes</button>--%>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModalBarber" tabindex="-1" role="dialog" aria-labelledby="exampleModalBarberTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalBarberTitle"><fmt:message key="barberModal.chooseEmployee"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container ">
                    <ul id="prodResponser" class="list-unstyled">
                    </ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <%--                    <button id="clickme" type="button" class="btn btn-primary">Save changes</button>--%>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModalService" tabindex="-1" role="dialog" aria-labelledby="exampleModalServiceTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalServiceTitle"><fmt:message key="serviceModal.chooseType"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container ">
                    <ul id="servicesList" class="list-unstyled">
                    </ul>
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
