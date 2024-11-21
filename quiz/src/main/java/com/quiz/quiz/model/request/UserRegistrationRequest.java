package com.quiz.quiz.model.request;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class UserRegistrationRequest {
    private String userName;
    private String password;
    private String email;
}
