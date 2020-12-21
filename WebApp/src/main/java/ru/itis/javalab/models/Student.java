package ru.itis.javalab.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Student {

    private long id;
    private String firstName;
    private String lastName;
    private int age;
}
