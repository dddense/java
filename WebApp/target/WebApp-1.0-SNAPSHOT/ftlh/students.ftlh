<html lang="ru">
<head>
    <title>Students</title>
</head>
<body>
<h1 <#if color??> style="color:${color.value}"</#if>>STUDENTS</h1>
<form action="/students" method="post">
    <input type="hidden" value="${_csrf_token}" name="_csrf_token">
    <label>
        <select name="color">
            <option value="red">RED</option>
            <option value="green">GREEN</option>
            <option value="blue">BLUE</option>
        </select>
    </label>
    <input type="submit" value="OK">
</form>
<table>
    <th>ID</th>
    <th>FIRST NAME</th>
    <th>LAST NAME</th>
    <th>AGE</th>
    <#list students as student>
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.age}</td>
        </tr>
    </#list>
</table>
</body>
</html>