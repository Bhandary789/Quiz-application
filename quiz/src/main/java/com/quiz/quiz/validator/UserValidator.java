package com.quiz.quiz.validator;

import com.quiz.quiz.dao.UserDao;
import com.quiz.quiz.exceptions.ValidationException;
import com.quiz.quiz.model.request.UserRegistrationRequest;
import com.quiz.quiz.model.response.ErrorDto;
import com.quiz.quiz.util.ErrorConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\(?([0-9]{3})\\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private final UserDao userDao;

    public void requestValidator(UserRegistrationRequest userRequest) {
        List<ErrorDto> errorDtoList = new ArrayList<>();
        if (ObjectUtils.isEmpty(userRequest)) {
            errorDtoList.add(new ErrorDto(ErrorConstants.MANDATORY_ERROR_CODE, MessageFormat.format(ErrorConstants.MANDATORY_ERROR_MESSAGE, "request body can't be empty")));
        }
        if (ObjectUtils.isEmpty(userRequest.getUserName())) {
            errorDtoList.add(new ErrorDto(ErrorConstants.MANDATORY_ERROR_CODE, MessageFormat.format(ErrorConstants.MANDATORY_ERROR_MESSAGE, "userName")));
        }
        if (ObjectUtils.isEmpty(userRequest.getEmail())) {
            errorDtoList.add(new ErrorDto(ErrorConstants.MANDATORY_ERROR_CODE, MessageFormat.format(ErrorConstants.MANDATORY_ERROR_MESSAGE, "email")));
        }
        if (!EMAIL_PATTERN.matcher(userRequest.getEmail()).matches()) {
            errorDtoList.add(new ErrorDto(ErrorConstants.INVALID_ERROR_CODE, MessageFormat.format(ErrorConstants.INVALID_ERROR_CODE_MESSAGE, "email")));
        }
        if (userDao.existsByUserName(userRequest.getUserName())){
            errorDtoList.add(new ErrorDto(ErrorConstants.ALREADY_PRESENT_ERROR_CODE, MessageFormat.format(ErrorConstants.ALREADY_PRESENT_ERROR_MESSAGE, "duplicate email ", "userName")));
        }
        if (userDao.existsByEmail(userRequest.getEmail())){
            errorDtoList.add(new ErrorDto(ErrorConstants.ALREADY_PRESENT_ERROR_CODE, MessageFormat.format(ErrorConstants.ALREADY_PRESENT_ERROR_MESSAGE, "duplicate email ", "email")));
        }
        if (CollectionUtils.isNotEmpty(errorDtoList)) {
            throw new ValidationException(errorDtoList);
        }
    }
}
