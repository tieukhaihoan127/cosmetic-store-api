package com.vincent.beauty_shop.service.question;

import com.vincent.beauty_shop.entity.Question;
import com.vincent.beauty_shop.request.question.QuestionCreateRequest;
import com.vincent.beauty_shop.response.question.QuestionDTO;

import java.util.List;

public interface QuestionService {
    public List<QuestionDTO> getAllQuestions();

    QuestionDTO createQuestion(QuestionCreateRequest request);

    QuestionDTO getQuestionById(Long id);

    void deleteQuestion(Long id);
}
