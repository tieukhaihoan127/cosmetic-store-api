package com.vincent.beauty_shop.response.question;

import com.vincent.beauty_shop.response.answer.AnswerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDTO {
    private Long id;
    private String question;
    private LocalDateTime timestamp;
    private LocalDateTime createdAt;

    private String productName;
    private String clientName;

    private AnswerDTO answer;
}
