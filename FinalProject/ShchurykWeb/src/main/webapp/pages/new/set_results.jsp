<%-- 
    Document   : set_horse_place
    Created on : 22.04.2018, 9:04:36
    Author     : NotePad.by
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta login="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Выставление результатов</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="../../../../../../Jurets6/web/css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>
    <body>
        <script>
            $(document).ready(function() {
               $(".nav li").removeClass("active"); 
               $("#set_results").addClass("active");
            });
        </script>
        <jsp:include page="../navigation.jsp"/>
        <div class="container">
            <h1 align="center">Выставление результатов забега</h1>
            <div class="form-wrap">
                <form login="set_results_form" action="MainServlet" method="POST" class="form-inline">
                    <input type="hidden" value="set_results" login="page_name">
                    <div class="form-group mb-2">
                        <label for="race_name_input_for_result" class="sr-only">Название забега</label>
                        <input type="text" class="form-control" id="race_name_input_for_result" login="race_name_input_for_result" placeholder="Название забега">
                    </div>
                    <div class="form-group mx-sm-3 mb-2">
                        <label for="horse_name_input_for_result" class="sr-only">Имя лошади</label>
                        <input type="text" class="form-control" id="horse_name_input_for_result" login="horse_name_input_for_result" placeholder="Имя лошади">
                    </div>
                    <div class="form-group mx-sm-3 mb-2">
                        <label for="place_input_for_result" class="sr-only">Место</label>
                        <input type="text" class="form-control" id="place_input_for_result" login="place_input_for_result" placeholder="Место">
                    </div>
                    <div class="form-group mx-sm-3 mb-2">
                        <button type="submit" class="btn btn-primary">Отправить</button>
                    </div>
                </form>
            </div>
            <c:choose>
                <c:when test="${result == true}">
                    <font color="green">Результат лошади зафиксирован.</font>
                </c:when>
                <c:when test="${result == false}">
                    <font color="red">Результат лошади не был зафиксирован.</font>
                </c:when>
            </c:choose>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    </body>
    
     <jsp:include page="../footer.jsp"/>
</html>
