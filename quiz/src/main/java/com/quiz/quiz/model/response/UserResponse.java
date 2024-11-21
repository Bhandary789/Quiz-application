package com.quiz.quiz.model.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserResponse {
    private UUID id;
    private String userName;
    private String email;
    private Double score;
}
