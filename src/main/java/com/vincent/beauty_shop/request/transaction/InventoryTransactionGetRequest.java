package com.vincent.beauty_shop.request.transaction;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryTransactionGetRequest {

    @NotBlank
    private String type;

    @NotBlank
    private Long storeId;

    private LocalDateTime start;

    private LocalDateTime end;

    private Long variantId;

    private Long creatorId;
}
