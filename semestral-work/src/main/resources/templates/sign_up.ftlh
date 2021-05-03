<html>
<#import "spring.ftl" as spring>
<head>
    <title>Sign up</title>
</head>
<body>
<@spring.bind "userForm"/>
<form method="post" action="/signUp">
<#--    <input type='hidden' value='${_csrf.token}' name='${_csrf.parameterName}'/>-->
    <div class="container">
        <p>Please fill in this form to create an account</p>
        <label>Email</label>
        <@spring.formInput "userForm.email"/>
        <label>Password</label>
        <@spring.formPasswordInput "userForm.password"/>
        <label>Phone</label>
        <@spring.formInput "userForm.phone" 'placeholder="7 123 123 12 12"'/>
        <label>Username</label>
        <@spring.formInput "userForm.username"/>
        <input type="submit" value="Enter">
    </div>
    <div class="container signin">
        <p>Already have an account? <a href="/signIn">Sign in</a>.</p>
    </div>
</form>
</body>
</html>