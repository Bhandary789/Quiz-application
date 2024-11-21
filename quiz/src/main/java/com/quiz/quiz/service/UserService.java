package com.quiz.quiz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.quiz.dao.QuizDao;
import com.quiz.quiz.dao.UserDao;
import com.quiz.quiz.entity.Quiz;
import com.quiz.quiz.entity.User;
import com.quiz.quiz.exceptions.QuizException;
import com.quiz.quiz.model.request.QuizRequest;
import com.quiz.quiz.model.request.UserRegistrationRequest;
import com.quiz.quiz.model.response.ResponseDto;
import com.quiz.quiz.model.response.UserResponse;
import com.quiz.quiz.util.ErrorConstants;
import com.quiz.quiz.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ValidationException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.quiz.quiz.util.AppConstants.RESPONSE_SUCCESS;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserValidator userValidator;
    private final ObjectMapper mapper;
    private final UserDao userDao;
    private final QuizDao quizDao;

    public ResponseDto<String> saveUser(UserRegistrationRequest userRequest) {
        userValidator.requestValidator(userRequest);
        User user = mapper.convertValue(userRequest, User.class);
        userDao.saveUser(user);
        return ResponseDto.<String>builder()
                .status(RESPONSE_SUCCESS)
                .data(List.of("user registered successfully"))
                .build();
    }

    public Optional<User> getUserByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    public double takeQuiz(UUID userId, QuizRequest quizRequest) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new QuizException(ErrorConstants.NOT_FOUND_ERROR_CODE, MessageFormat.format(ErrorConstants.NOT_FOUND_ERROR_MESSAGE, "User")));

        List<Quiz> quizzes = quizDao.getAllQuizzes();
        double score = 0.0;

        if (user.getScore() == null) {
            List<String> userAnswers = quizRequest.getUserAnswers();

            if (userAnswers.size() != quizzes.size()) {
                throw new QuizException(ErrorConstants.INVALID_INPUT_ERROR_CODE, "The number of answers does not match the number of quizzes.");
            }

            for (int i = 0; i < quizzes.size(); i++) {
                Quiz quiz = quizzes.get(i);
                String userAnswer = userAnswers.get(i);

                if (userAnswer.equalsIgnoreCase(quiz.getCorrectAnswer())) {
                    score += 1.0;
                } else {
                    score -= 0.25;
                }
            }
        } else {
            throw new QuizException(ErrorConstants.ALREADY_PRESENT_ERROR_CODE, MessageFormat.format(ErrorConstants.ALREADY_PRESENT_ERROR_MESSAGE, "participated", "user"));
        }

        user.setScore(score);
        userDao.saveUser(user);
        return score;
    }


}
