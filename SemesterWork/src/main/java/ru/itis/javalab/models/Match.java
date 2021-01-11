package ru.itis.javalab.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Match {

    private Integer id;
    private Integer firstId;
    private Integer secondId;
    private Integer firstScore;
    private Integer secondScore;
    private String date;
    private String status;
}
