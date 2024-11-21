package com.quiz.quiz.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.quiz.dao.QuizDao;
import com.quiz.quiz.entity.Quiz;
import com.quiz.quiz.model.response.QuestionResponse;
import com.quiz.quiz.model.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.quiz.quiz.util.AppConstants.RESPONSE_SUCCESS;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {
    private final QuizDao quizDao;
    private final ObjectMapper objectMapper;
    public List<Quiz> getAllQuizzes() {
        return quizDao.getAllQuizzes();
    }
    public ResponseDto<QuestionResponse> getAllQuestions() {
        List<Quiz> quizzes = quizDao.getAllQuizzes();
        List<QuestionResponse> questionResponseList = objectMapper.convertValue(
                quizzes,
                new TypeReference<List<QuestionResponse>>() {}
        );
        return ResponseDto.<QuestionResponse>builder()
                .status(RESPONSE_SUCCESS)
                .data(questionResponseList)
                .build();
    }
    public Quiz createQuiz(Quiz quiz) {
        return quizDao.saveQuiz(quiz);
    }
}
