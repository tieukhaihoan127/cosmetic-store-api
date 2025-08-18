package com.vincent.beauty_shop.request.brand;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandUpdateRequest {

    private String title;

    private String logo;

    private String bannerImage;

    private String description;
}
