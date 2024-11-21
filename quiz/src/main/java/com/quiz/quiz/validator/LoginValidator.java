package com.quiz.quiz.validator;

import com.quiz.quiz.config.ServiceConfig;
import com.quiz.quiz.dao.UserDao;
import com.quiz.quiz.entity.User;
import com.quiz.quiz.exceptions.QuizException;
import com.quiz.quiz.exceptions.ValidationException;
import com.quiz.quiz.model.request.LoginRequest;
import com.quiz.quiz.model.response.ErrorDto;
import com.quiz.quiz.util.ErrorConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginValidator {
    private final UserDao userDao;
    public  void requestValidation(LoginRequest request) {
        User user = userDao.findByUserName(request.getUserName()).orElseThrow(() -> new QuizException(ErrorConstants.NOT_FOUND_ERROR_CODE, MessageFormat.format(ErrorConstants.NOT_FOUND_ERROR_MESSAGE, "User")));
        List<ErrorDto> errorMessages = new ArrayList<>();
        if (StringUtils.isEmpty(request.getUserName())) {
            errorMessages.add(new ErrorDto(ErrorConstants.MANDATORY_ERROR_CODE, MessageFormat.format(ErrorConstants.MANDATORY_ERROR_MESSAGE, "userName")));
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            errorMessages.add(new ErrorDto(ErrorConstants.MANDATORY_ERROR_CODE, MessageFormat.format(ErrorConstants.MANDATORY_ERROR_MESSAGE, "password")));
        } else if (!request.getUserName().equals(user.getUsername())){
            errorMessages.add(new ErrorDto(ErrorConstants.INVALID_ERROR_CODE, MessageFormat.format(ErrorConstants.INVALID_ERROR_CODE_MESSAGE, "userName")));
        }
        else if (!request.getPassword().equals(user.getPassword())){
            errorMessages.add(new ErrorDto(ErrorConstants.INVALID_ERROR_CODE, MessageFormat.format(ErrorConstants.INVALID_ERROR_CODE_MESSAGE, "password")));
        }
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(errorMessages);
        }
    }
}