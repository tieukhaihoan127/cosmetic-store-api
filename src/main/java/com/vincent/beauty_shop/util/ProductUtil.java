package com.vincent.beauty_shop.util;

import com.vincent.beauty_shop.entity.Product;

public class ProductUtil {

    public ProductUtil() {}

    public static double calculateProductMinPrice(Product product){
        if (product == null || product.getProductVariants() == null) {
            return 0.0;
        }

        return product.getProductVariants().stream().mapToDouble(variant -> variant.getPrice()*(1 - (variant.getDiscountPercentage()/100))).min().orElse(0.0);
    }

}
