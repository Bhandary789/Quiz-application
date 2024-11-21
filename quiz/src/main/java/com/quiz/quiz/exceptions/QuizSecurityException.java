package com.quiz.quiz.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QuizSecurityException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
}
