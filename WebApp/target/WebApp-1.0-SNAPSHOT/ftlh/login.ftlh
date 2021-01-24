<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form method="post">
    <div class="container">

        <h1>Login</h1>
        <p>Enter your credentials.</p>

        <hr>
        <label for="username"><b>Password</b></label>
        <input type="username" placeholder="Username" name="username" required>
        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Password" name="password" required>
        <hr>
        <input type="hidden" value="${_csrf_token}" name="_csrf_token">
        <input type="submit" value="Enter">
    </div>
</form>
</body>
</html>