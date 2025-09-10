package com.vincent.beauty_shop.controller.client;

import com.vincent.beauty_shop.entity.Answer;
import com.vincent.beauty_shop.request.answer.AnswerCreateRequest;
import com.vincent.beauty_shop.service.answer.AnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/answers")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<Answer> addAnswer(@Valid @RequestBody AnswerCreateRequest request) {
        Answer answer = answerService.createAnswer(request);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }
}
