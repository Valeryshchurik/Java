<%-- 
    Document   : footer
    Created on : 22.04.2018, 10:23:22
    Author     : NotePad.by
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<footer class="page-footer navbar-fixed-bottom font-small blue pt-4 mt-4">
    <div class="container-fluid text-center text-md-left">
        <div class="row">
            <div class="col-md-6">
                <h5 class="text-uppercase">Статистика</h5>
                <ul class="list-unstyled">
                    <li><c:out value="Последнее посещение: ${cookie.last_session_date.value}"></c:out></li>
                    <li><c:out value="Количество посещений: ${cookie.visit_count.value}"></c:out></li>
                </ul>
            </div>

            <%--<div class="col-md-6">--%>
                <%--<h5 class="text-uppercase">Навигация</h5>--%>
                <%--<ul class="list-unstyled">--%>
                    <%--<li>--%>
                        <%--<a href="../../../../../../Jurets6/web/index.jsp">Главная</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="new/horses_by_race.jsp">Лошади забега</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="new/races_by_date.jsp">Забеги дня</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="new/winner_clients.jsp">Выигравшие клиенты</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="new/register_horse.jsp">Регистрация лошади</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="new/set_results.jsp">Выставление результатов</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        </div>
    </div>
    
    <div class="footer-copyright py-3 text-center">
        © 2018 Copyright: Valery Shchuryk
    </div>

</footer>
