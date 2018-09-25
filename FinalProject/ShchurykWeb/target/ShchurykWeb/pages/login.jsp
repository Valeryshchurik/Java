<%--
  Created by IntelliJ IDEA.
  User: msi
  Date: 03.04.2018
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>--%>
<%--<html><head><title>Login</title></head>--%>
<%--<body>--%>
<%--<form login="loginForm" method="POST" action="controller">--%>
    <%--<input type="hidden" login="command" value="logIn" />--%>
    <%--Login:<br/>--%>
    <%--<input type="text" login="logIn" value=""/>--%>
    <%--<br/>Password:<br/>--%>
    <%--<input type="password" login="password" value=""/>--%>
    <%--<br/>--%>
    <%--${errorLoginPassMessage}--%>
    <%--<br/>--%>
    <%--${wrongAction}--%>
    <%--<br/>--%>
    <%--${nullPage}--%>
    <%--<br/>--%>
    <%--<input type="submit" value="Log in"/>--%>
<%--</form><hr/>--%>
<%--</body></html>--%>

<%--
    Document   : logIn
    Created on : 28.04.2018, 11:19:35
    Author     : NotePad.by
--%>

<%--
    Document   : logIn
    Created on : 28.04.2018, 11:19:35
    Author     : NotePad.by
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta login="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Вход</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<script>
    $(document).ready(function() {
        $(".nav li").removeClass("active");
        $("#logIn").addClass("active");
    });
</script>
<jsp:include page="navigation.jsp"/>
<div class="container">
    <h1 align="center">Вход</h1>
    <div class="form-wrap">
        <form login="login_form" action="controller" method="POST">
            <input type="hidden" name="command" value="login">
            <div class="row">
                <div class="form-group col-6">
                    <label for="username_input_for_login">Имя пользователя</label>
                    <input type="text" class="form-control" id="username_input_for_login" name="username_input_for_login" placeholder="Имя пользователя">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-6">
                    <label for="password_input_for_login">Пароль</label>
                    <input type="password" class="form-control" id="password_input_for_login" name="password_input_for_login" placeholder="Пароль">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-2">
                    <button type="submit" class="btn btn-primary">Отправить</button>
                </div>
            </div>
        </form>
    </div>
    <c:if test="${result == false}">
        <font color="red">Неверное имя пользователя или пароль.</font>
    </c:if>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>

<jsp:include page="footer.jsp"/>
</html>



