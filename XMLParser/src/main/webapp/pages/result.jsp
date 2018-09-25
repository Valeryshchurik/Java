<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html><head>
    <%--<title><fmt:message key="title" bundle="${rb}"/></title>--%>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>${Result}</title>
</head>
<body>
<table>
    <tr>
        <td>ID</td>
        <td>Type</td>
        <td>Country</td>
        <td>Days</td>
        <td>Cost</td>
        <td>Details</td>
        <%--<td><c:out value="${item}" /></td>--%>
    </tr>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.getClass().getSimpleName()}</td>
            <td>${item.country}</td>
            <td>${item.numberDays}</td>
            <td>${item.cost}</td>
            <td>${item.toSecondaryString()}</td>
                <%--<td><c:out value="${item}" /></td>--%>
        </tr>
    </c:forEach>
</table>
<br/>
<form action="${pageContext.request.contextPath}/index.jsp">
    <input type="submit" value="${back}"/>
</form>
</body>
</html>
