package com.quiz.quiz.service;


import com.quiz.quiz.exceptions.QuizException;
import com.quiz.quiz.util.ErrorConstants;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service

public class AuthenticationUserDetailsService {
    private final UserService userService;

    public AuthenticationUserDetailsService(UserService userService) {
        this.userService = userService;
    }
    public UserDetailsService userDetailsService() {
        return username -> userService.getUserByUserName(username)
                .orElseThrow(() -> new QuizException(ErrorConstants.NOT_FOUND_ERROR_CODE, MessageFormat.format(ErrorConstants.NOT_FOUND_ERROR_MESSAGE, "User")));
    }
}
