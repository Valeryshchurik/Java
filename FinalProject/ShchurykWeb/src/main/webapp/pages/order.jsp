<%--
    Document   : horses_by_race
    Created on : 21.04.2018, 11:36:36
    Author     : NotePad.by
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/custom.tld" prefix="ctg" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta login="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Заказы</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../../../../../Jurets6/web/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<script>
    $(document).ready(function() {
        $(".nav li").removeClass("active");
        $("#horses_by_race").addClass("active");
    });
</script>
<jsp:include page="navigation.jsp"/>
<div class="container">
    <h1 align="center">Заказы клиента</h1>
    <div class="form-wrap">
        <form login="orders_form" action="controller" method="POST" class="form-inline">
            <div class="form-group mb-2">
                <input type="hidden" value="orders" name="command">
                <label for="client_input" class="sr-only">имя клиента</label>
                <input type="text" class="form-control" id="client_input" name="orders" placeholder="Имя клиента">
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <button type="submit" class="btn btn-primary">Отправить</button>
            </div>
        </form>
    </div>
    <ctg:order-table/>
    <%--<table class="table">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th scope="col">id</th>--%>
            <%--<th scope="col">Название</th>--%>
            <%--<th scope="col">Страна</th>--%>
            <%--<th scope="col">Дата начала</th>--%>
            <%--<th scope="col">Дата окончания</th>--%>
            <%--<th scope="col">Тип тура</th>--%>
            <%--<th scope="col">Цена</th>--%>
            <%--<th scope="col">Скидки обладателям карты друга</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<c:forEach items="${result}" var="tour">--%>
            <%--<tr>--%>
                <%--<td>${tour.getTourId()}</td>--%>
                <%--<td>${tour.getName()}</td>--%>
                <%--<td>${tour.getCountry()}</td>--%>
                <%--<td>${tour.getStartDate()}</td>--%>
                <%--<td>${tour.getEndDate()}</td>--%>
                <%--<td>${tour.getType()}</td>--%>
                <%--<td>${tour.getPrice()}</td>--%>
                <%--<td>${tour.getDiscounts()}</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--</tbody>--%>
    <%--</table>--%>
    <div class="clearFloat"></div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>

<jsp:include page="footer.jsp"/>
</html>

