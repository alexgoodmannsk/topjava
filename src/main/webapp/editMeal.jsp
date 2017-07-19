<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit</title>
</head>
<body>
    <h2>Edit Meal</h2>
    <form action="meals" method="post">
        <table>
            <tr>
                <td></td>
                <td>Date</td>
                <td>Description</td>
                <td>Calories</td>
            </tr>
            <tr>
                <td><input type="text" name="id" value="${editMeal.id}" hidden/></td>
                <td><input type="datetime-local" name="dateTime" value="${editMeal.dateTime}"/></td>
                <td><input type="text" name="description" value="${editMeal.description}"/></td>
                <td><input type="text" name="calories" value="${editMeal.calories}"/></td>
                <td><input type="submit" value="OK"></td>
            </tr>
        </table>
    </form>
</body>
</html>
