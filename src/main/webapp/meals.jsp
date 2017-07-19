<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
    <%--Создаем стиль для нашей таблицы--%>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 2px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 2px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table class="tg">
    <tr>
        <th>ID</th>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:if test="${!empty meals}">
        <c:forEach var="meal" items="${meals}" >
            <tr style="color: <c:out value="${meal.isExceed() == true ? 'red' : '#7cff20'}" />">
                <td>${meal.id}</td>
                <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="<c:url value='/meals?action=edit&id=${meal.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/meals?action=delete&id=${meal.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<h3><a href="<c:url value='/meals?action=add'/>">Добавить новый прием пищи</a></h3>
</body>
</html>
