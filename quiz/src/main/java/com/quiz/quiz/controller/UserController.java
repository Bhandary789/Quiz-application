package com.quiz.quiz.controller;

import com.quiz.quiz.model.request.LoginRequest;
import com.quiz.quiz.model.request.QuizRequest;
import com.quiz.quiz.model.request.UserRegistrationRequest;
import com.quiz.quiz.model.response.LoginResponse;
import com.quiz.quiz.model.response.ResponseDto;
import com.quiz.quiz.service.LoginService;
import com.quiz.quiz.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Login API")
    public ResponseDto<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Received request for login {}", loginRequest);
        return loginService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseDto<String> saveUser(@RequestBody UserRegistrationRequest userRequest) {
        log.info("received request for user registration");
        return userService.saveUser(userRequest);
    }
    @PostMapping("/participate/{userId}")
    public double takeQuiz(@PathVariable UUID userId, @RequestBody QuizRequest quizRequest) {
        log.info("received request for user registration");
        return userService.takeQuiz(userId, quizRequest);
    }
}
