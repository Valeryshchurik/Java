
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html><head><title>JSP Timing</title></head>
<body>
<jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
<form name="Simple" action="timeaction" method="POST">
    <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
    <input type="submit" name="button" value="Посчитать время"/>
</form>
<h2>${currentLoc} - ${locale}</h2>
<form action="Localisation" method="post">
    <select name="localisation">
        <option value="en_US">English</option>
        <option value="ru_RU">Русский</option>
    </select>
    <br/>
    <input type = "submit" value = "${loc}" />
</form>
<br/>
${choosingFile}: <br />
<form action = "UploadFiles" method = "post" enctype = "multipart/form-data">
    <select name="parserType">
        <option value="DOM">DOM</option>
        <option value="SAX">SAX</option>
        <option value="STAX">STAX</option>
    </select>
    <input type = "file" name = "file" size = "50" />
    <br/>
    <input type = "submit" value = ${acceptFile} />
</form>
<br/>
</body></html>

