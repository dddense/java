package ru.itis.semestralwork.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class PostForm {

    @NotEmpty(message = "Post shouldn't be empty")
    @NotNull
    private String content;
}
