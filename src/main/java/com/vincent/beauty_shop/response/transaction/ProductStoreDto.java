package com.vincent.beauty_shop.response.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductStoreDto {
    private Long productId;
    private int stock;
    private String productTitle;
    private String thumbnail;
    private String SKU;
    private Double price;
}
