
@HtmlForm(method = "post", action = "/users")
public class User {

    @HtmlInput(name = "email", placeholder = "Email")
    private String email;
    @HtmlInput(name = "password", placeholder = "Password")
    private String password;
    @HtmlInput(name = "nickname", placeholder = "Name")
    private String nickname;
}
