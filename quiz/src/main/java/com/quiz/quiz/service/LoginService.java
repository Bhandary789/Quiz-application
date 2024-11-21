package com.quiz.quiz.service;

import com.quiz.quiz.model.request.LoginRequest;
import com.quiz.quiz.model.response.LoginResponse;
import com.quiz.quiz.model.response.ResponseDto;
import com.quiz.quiz.security.AuthenticationService;
import com.quiz.quiz.validator.LoginValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final LoginValidator loginValidator;

    private final AuthenticationService authenticationService;

    public ResponseDto<LoginResponse> login(LoginRequest requestBody) {
        loginValidator.requestValidation(requestBody);
        LoginResponse loginResponse = new LoginResponse(authenticationService.signIn(requestBody));
        return ResponseDto.<LoginResponse>builder().status(0).data(List.of(loginResponse)).build();
    }
}
