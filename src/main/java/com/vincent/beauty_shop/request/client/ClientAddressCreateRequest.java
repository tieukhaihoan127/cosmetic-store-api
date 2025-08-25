package com.vincent.beauty_shop.request.client;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientAddressCreateRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String title;

    @NotBlank
    private String phone;

    @NotBlank
    private String city;

    @NotBlank
    private String district;

    @NotBlank
    private String ward;

    @NotBlank
    private String address;

    private boolean defaultAddress;
}
