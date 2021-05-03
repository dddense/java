<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>News</title>
    <link rel="stylesheet", href="../css/style.css">
</head>
<body>
<h2>News</h2>
<br>
<#list posts as post>
    <br>
        <h1>${post.author}</h1>
        <p>${post.body}</p>
    </br>
</#list>
</br>
<table>
    <tr>
        <td></td>
    </tr>
</table>
</body>
</html>