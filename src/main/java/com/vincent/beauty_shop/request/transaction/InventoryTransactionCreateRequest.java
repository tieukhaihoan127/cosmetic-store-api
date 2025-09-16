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
public class InventoryTransactionCreateRequest {
    private int quantity;

    private String note;

    private Long accountId;

    private String type;

    private Long storeId;

    private Long variantId;
}
