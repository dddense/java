<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>
<form method="post">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="username"><b>Password</b></label>
        <input type="username" placeholder="Username" name="username" required>
        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Password" name="password" required>
        <hr>
        <input type="hidden" value="${_csrf_token}" name="_csrf_token">
        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <input type="submit" value="Enter">
    </div>

    <div class="container signin">
        <p>Already have an account? <a href="/login.ftlh">Sign in</a>.</p>
    </div>
</form>
</body>
</html>
