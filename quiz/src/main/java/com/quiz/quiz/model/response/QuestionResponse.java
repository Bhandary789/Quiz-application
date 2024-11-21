package com.quiz.quiz.model.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class QuestionResponse {
    private UUID id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}
