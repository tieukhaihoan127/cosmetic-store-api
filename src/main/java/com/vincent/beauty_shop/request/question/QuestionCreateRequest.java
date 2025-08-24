package com.vincent.beauty_shop.request.question;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateRequest {

    @NotBlank
    private String question;

    @NotBlank
    private Long productId;

    @NotBlank
    private Long clientId;

}
