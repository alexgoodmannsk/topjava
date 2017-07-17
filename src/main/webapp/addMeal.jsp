<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Meals</title>
</head>
<body>
<h2>Edit Meal</h2>
    <form action="addMeal" method="post">
        <table>
            <tr>
                <td>Date</td>
             <td>Description</td>
                <td>Calories</td>
            </tr>
            <tr>
                <td><input type="datetime-local" name="dateTime"/></td>
                <td><input type="text" name="description"/></td>
                <td><input type="text" name="calories"/></td>
                <td><input type="submit" value="OK"></td>
            </tr>
        </table>
    </form>
</body>
</html>
