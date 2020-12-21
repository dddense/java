package ru.itis.javalab.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {

    private long id;
    private String username;
    private String password;
    private String uuid;
}