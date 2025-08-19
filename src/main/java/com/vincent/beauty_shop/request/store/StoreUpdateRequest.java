package com.vincent.beauty_shop.request.store;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreUpdateRequest {
    @NotBlank
    private String name;

    private String hotline;

    @NotBlank
    private String city;

    @NotBlank
    private String ward;

    @NotBlank
    private String district;

    @NotBlank
    private String address;

    private LocalTime openingHour;

    private LocalTime closingHour;

    private String image;
}
