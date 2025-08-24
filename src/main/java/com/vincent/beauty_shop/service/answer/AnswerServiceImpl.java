package com.vincent.beauty_shop.service.answer;

import com.vincent.beauty_shop.entity.Answer;
import com.vincent.beauty_shop.entity.Question;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.repository.AnswerRepository;
import com.vincent.beauty_shop.repository.QuestionRepository;
import com.vincent.beauty_shop.request.answer.AnswerCreateRequest;
import com.vincent.beauty_shop.request.answer.AnswerUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService{

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Answer createAnswer(AnswerCreateRequest request) {
        Question question = questionRepository.findById(request.getQuestionId()).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + request.getQuestionId()));

        Answer answer = Answer.builder().answer(request.getAnswer()).timestamp(LocalDateTime.now()).question(question).build();
        question.setAnswer(answer);
        questionRepository.save(question);

        return answer;
    }

    @Override
    public Answer updateAnswer(Long id, AnswerUpdateRequest request) {
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + id));
        answer.setAnswer(request.getAnswer());
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + id));
        Question question = answer.getQuestion();
        if (question != null) {
            question.setAnswer(null);
            questionRepository.save(question);
        }
        answerRepository.delete(answer);
    }
}
