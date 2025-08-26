package com.vincent.beauty_shop.request.rating;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingCreateRequest {

    private String image;

    private String detailDesc;

    @NotBlank
    private String shortDesc;

    @NotBlank
    private String email;

    @NotBlank
    private String nickname;

    @NotNull
    private double star;

    private boolean isReview;

    @NotNull
    private Long productId;
}
