package com.vincent.beauty_shop.service.answer;

import com.vincent.beauty_shop.entity.Answer;
import com.vincent.beauty_shop.request.answer.AnswerCreateRequest;
import com.vincent.beauty_shop.request.answer.AnswerUpdateRequest;

public interface AnswerService {
    Answer createAnswer(AnswerCreateRequest request);

    Answer updateAnswer(Long id, AnswerUpdateRequest request);

    void deleteAnswer(Long id);
}
