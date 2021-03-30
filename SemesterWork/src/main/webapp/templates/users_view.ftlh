<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../static/css/style.css">
    <title>Users</title>
</head>
<body>
<table>
    <#list users as user>
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
        </tr>
    </#list>
</table>
</body>
</html>