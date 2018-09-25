<%-- 
    Document   : navigation
    Created on : 27.04.2018, 7:51:59
    Author     : NotePad.by
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="roles" class="com.shchuryk.model.UserType" scope="session" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
      <ul class="nav navbar-nav nav-right">
        <a id="index" class="nav-item nav-link" href="main.jsp">Главная</a>
        <a id="all_tours" class="nav-item nav-link" href="tours.jsp">Все туры</a>
        <a id="burning_tours" class="nav-item nav-link" href="burnings.jsp">Горящие путёвки</a>
        <c:if test="${not empty sessionScope.user}">
            <c:if test="${(not empty roles) and (sessionScope.user.userType eq 1)}">
                <%--<a id="show_users" class="nav-item nav-link" href="users.jsp">Показать клиентов</a>--%>
                <a id="show_users" class="nav-item nav-link" href="order.jsp">Показать заказы клиента</a>
            </c:if>
            <a id="greeting" class="nav-item nav-link" href="#"><c:out value="Добрый день, ${sessionScope.user.login}!"></c:out></a>
            <a id="logout" class="nav-item nav-link" href="#">Выход</a>
            <form id="logout_form" action="controller" method="POST">
                <input type="hidden" value="logout" name="command">
            </form>
            <script>
                document.getElementById("logout").onclick = function() {
                    document.getElementById("logout_form").submit();
                }
            </script>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <a id="register" class="nav-item nav-link" href="register.jsp">Регистрация</a>
            <a id="login" class="nav-item nav-link" href="login.jsp">Вход</a>
        </c:if>
      </ul>
    </div>
</nav>
        
