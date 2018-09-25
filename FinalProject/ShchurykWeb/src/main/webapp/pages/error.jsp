<%--<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<html><head><title>Error Page</title></head>--%>
<%--<body>--%>
<%--Request from ${pageContext.errorData.requestURI} is failed--%>
<%--<br/>--%>
<%--Servlet login or type: ${pageContext.errorData.servletName}--%>
<%--<br/>--%>
<%--Status code: ${pageContext.errorData.statusCode}--%>
<%--<br/>--%>
<%--Exception: ${pageContext.errorData.throwable}--%>
<%--</body></html>--%>
<%--&lt;%&ndash; --%>
    <%--Document   : error--%>
    <%--Created on : 02.05.2018, 9:51:56--%>
    <%--Author     : NotePad.by--%>
<%--&ndash;%&gt;--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta login="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Ошибка</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<h1>OOPS!</h1>
<h3>${err}</h3>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>
</html>
