package com.vincent.beauty_shop.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantUpdateRequest {

    private double discountPercentage;

    private double price;

    private String sku;

    private String color;

    private String productId;

    private List<String> images;
}
