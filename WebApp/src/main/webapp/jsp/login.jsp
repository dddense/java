<%--
  Created by IntelliJ IDEA.
  User: densh
  Date: 25.10.2020
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Authorization</h1>
<form method="post" action="/login">
    <fieldset>
        <legend>Credentials</legend>
        <label>Username<input name="username" type="text" required></label>
        <label>Password<input name="password" type="password" required></label>
    </fieldset>
    <input type="submit" value="Save">
</form>
</body>
</html>
