package com.vincent.beauty_shop.controller.admin;

import com.vincent.beauty_shop.entity.Answer;
import com.vincent.beauty_shop.request.answer.AnswerCreateRequest;
import com.vincent.beauty_shop.request.answer.AnswerUpdateRequest;
import com.vincent.beauty_shop.service.answer.AnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
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

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody AnswerUpdateRequest request) {
        Answer answer = answerService.updateAnswer(id, request);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.ok("Answer successfully deleted");
    }
}
