<html>
<#import "spring.ftl" as spring/>
<head>
    <title><@spring.message 'sign_in.title'/></title>
    <style>
        .error {
            color : #BF4123;
        }
    </style>
</head>
<body>
<h2><a href="?lang=en">EN</a></h2>
<h2><a href="?lang=ru">RU</a></h2>
<h1><@spring.message 'sign_in.title'/></h1>
<@spring.bind "userForm"/>
<#if namesErrorMessage??>
    <p style=".error">${namesErrorMessage}</p>
</#if>
<form method="post" action="/signIn">
    <div class="container">
        <p>Please fill in this form to create an account.</p>
        <hr>
        <label><@spring.message 'sign_up.field.email'/></label>
        <@spring.formInput "userForm.email"/>
        <@spring.showErrors "<br " "error"/>

        <label><@spring.message 'sign_up.field.password'/></label>
        <@spring.formPasswordInput "userForm.password"/>
        <@spring.showErrors "<br>" "error"/>

        <label for="remember-me">
            <input type="checkbox" name="remember-me"><@spring.message 'remember-me'/>
        </label>

        <hr>
        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <input type="submit" value="Enter">

        <div class="container signin">
            <p>Doesn't have an account yet? <a href="/signUp">Sign in</a>.</p>
        </div>
    </div>
</form>
</body>
</html>