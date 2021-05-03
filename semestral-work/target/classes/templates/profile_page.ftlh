<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" , href="../css/style.css">
</head>
<body>
<h1>Profile</h1>
<h2>${user.username}</h2>
<h2>${user.id}</h2>
<form action="/profile/${user.id}" method="POST">
<#--    <input type='hidden' value='${_csrf.token}' name='${_csrf.parameterName}'/>-->
    <input type="submit" value="Follow">
</form>
<#list posts as post>
    <br>
    <h1>${post.author}</h1>
    <p>${post.body}</p>
    </br>
</#list>
</body>
</html>