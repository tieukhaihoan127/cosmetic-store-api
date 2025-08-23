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
public class ProductCreateRequest {

    @NotBlank
    private String title;

    private String description;

    private String origin;

    private boolean isLuxury;

    private boolean isFreeship;

    @NotBlank
    private String thumbnail;

    @NotBlank
    private Long brandId;

    @NotBlank
    private List<Long> categoryIds;

    private List<ProductVariantCreateRequest> variants;

}
