</body></html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<!DOCTYPE html>
<html lang="${language}">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta login="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Главная страница</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<script>
    $(document).ready(function() {
        $(".nav li").removeClass("active");
        $("#index").addClass("active");
    });
</script>

<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
    </select>
</form>


<jsp:include page="navigation.jsp"/>
<div class="container">
    <h1 align="center"><fmt:message key="hello" /></h1>
    <p>
        Добро пожаловать на сайт нашего турагентства.
    </p>
    <p>На этом сайте вы можете:</p>
    <ul type="disk">
        <li>Просмотреть список туров</li>
        <li>Просмотреть все горящие путёвки</li>
    </ul>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>

<jsp:include page="footer.jsp"/>
</html>