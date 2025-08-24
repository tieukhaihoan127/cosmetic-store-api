package com.vincent.beauty_shop.request.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCreateRequest {
    @NotBlank
    private String answer;

    @Positive
    private Long questionId;
}
