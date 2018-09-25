<%-- 
    Document   : winner_clients
    Created on : 21.04.2018, 13:31:24
    Author     : NotePad.by
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta login="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Выигравшие клиенты забега</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="../../../../../../Jurets6/web/css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>
    <body>
        <script>
            $(document).ready(function() {
               $(".nav li").removeClass("active"); 
               $("#winner_clients").addClass("active");
            });
        </script>
        <jsp:include page="../navigation.jsp"/>
        <div class="container">
            <h1 align="center">Список выигравших клиентов забега</h1>
            <div class="form-wrap">
                <form login="winner_clients_form" action="MainServlet" method="POST" class="form-inline">
                    <div class="form-group mb-2">
                        <input type="hidden" value="winner_clients" login="page_name">
                        <label for="race_name_input_for_bets" class="sr-only">Название забега</label>
                        <input type="text" class="form-control" id="race_name_input_for_bets" login="race_name_input_for_bets" placeholder="Название забега">
                    </div>
                    <div class="form-group mx-sm-3 mb-2">
                        <button type="submit" class="btn btn-primary">Отправить</button>
                    </div>
                </form>
            </div>
            <table class="table">
                <thead>
                    <tr>
                      <th scope="col">id</th>
                      <th scope="col">Имя клиента</th>
                      <th scope="col">Выигрыш</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result}" var="bet">
                        <tr>
                            <td>${bet.getId()}</td>
                            <td>${bet.getClientsByClientId().getClientName()}</td>
                            <td>${bet.getPayment()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    </body>
    
    <jsp:include page="../footer.jsp"/>
</html>
