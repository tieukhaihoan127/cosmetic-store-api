package com.vincent.beauty_shop.service.question;

import com.vincent.beauty_shop.entity.Question;

import java.util.List;

public interface QuestionService {
    public List<Question> getAllQuestions();

    Question createQuestion();

    Question getQuestionById(Long id);

    Question updateQuestion(Long id);

    void deleteQuestion(Long id);
}
