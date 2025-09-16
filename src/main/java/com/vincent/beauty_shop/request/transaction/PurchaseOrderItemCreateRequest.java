package com.vincent.beauty_shop.request.transaction;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderItemCreateRequest {
    private int quantity;

    private double costPrice;

    @NotBlank
    private Long variantId;
}
