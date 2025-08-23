package com.vincent.beauty_shop.request.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private double loyaltyPoint;
}
