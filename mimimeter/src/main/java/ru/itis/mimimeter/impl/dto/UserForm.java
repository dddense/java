package ru.itis.mimimeter.impl.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class UserForm {

    @NotNull
    @NotEmpty
    @Email(message = "Invalid email")
    private String email;

    private String password;

    private String username;

    private String phone;
}
