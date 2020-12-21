@HtmlForm(method = "post", action = "/students")
public class Student {

    @HtmlInput(name = "student", placeholder = "Email")
    private String student;
}
