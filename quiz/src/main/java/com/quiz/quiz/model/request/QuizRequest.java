package com.quiz.quiz.model.request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class QuizRequest {
    private List<String> userAnswers;
}
