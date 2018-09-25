<%-- 
    Document   : register
    Created on : 28.04.2018, 11:32:00
    Author     : NotePad.by
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta login="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Регистрация</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="../../../../../../Jurets6/web/css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>
    <body>
        <script>
            $(document).ready(function() {
               $(".nav li").removeClass("active"); 
               $("#register").addClass("active");
            });
        </script>
        <jsp:include page="navigation.jsp"/>
        <div class="container">
            <h1 align="center">Регистрация</h1>
            <div class="form-wrap">
                <form login="register_form" action="controller" method="POST">
                    <input type="hidden" value="register" name="command">
                    <div class="row">
                        <div class="form-group col-6">
                            <label for="client_name_input_for_register">Имя</label>
                            <input type="text" class="form-control" id="client_name_input_for_register" name="client_name_input_for_register" placeholder="Имя">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-6">
                            <label for="username_input_for_register">Имя пользователя</label>
                            <input type="text" class="form-control" id="username_input_for_register" name="username_input_for_register" placeholder="Имя пользователя">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-6">
                            <label for="username_input_for_register">Почтовый адрес</label>
                            <input type="text" class="form-control" id="mail_input_for_register" name="mail_input_for_register" placeholder="Почтовый адрес">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-6">
                            <label for="password_input_for_register">Пароль</label>
                            <input type="password" class="form-control" id="password_input_for_register" name="password_input_for_register" placeholder="Пароль">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-6">
                            <label for="confirm_password_input_for_register">Повторите пароль</label>
                            <input type="password" class="form-control" id="confirm_password_input_for_register" name="confirm_password_input_for_register" placeholder="Повторите пароль">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-2">
                            <button type="submit" class="btn btn-primary">Отправить</button>
                        </div>
                    </div>
                </form>
            </div>  
            <c:choose>
                <c:when test="${result == true}">
                    <font color="green">Регистрация прошла успешно.</font>
                </c:when>
                <c:when test="${result == false}">
                    <font color="red"><c:out value="${err}"></c:out></font>
                </c:when>
            </c:choose>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    </body>
    
    <jsp:include page="footer.jsp"/>
</html>

