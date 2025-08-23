package com.vincent.beauty_shop.request.product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {

    private String title;

    private String description;

    private String origin;

    private boolean isLuxury;

    private boolean isFreeship;

    private String thumbnail;

    private Long brandId;

    private List<Long> categoryIds;

    private List<ProductVariantUpdateRequest> variants;
}
