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
public class ClientAddressUpdateRequest {

    private String firstName;

    private String lastName;

    private String title;

    private String phone;

    private String city;

    private String district;

    private String ward;

    private String address;

    private boolean defaultAddress;
}
