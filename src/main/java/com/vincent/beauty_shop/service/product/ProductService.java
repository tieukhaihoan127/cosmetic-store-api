package com.vincent.beauty_shop.service.product;

import com.vincent.beauty_shop.entity.Product;
import com.vincent.beauty_shop.request.product.ProductCreateRequest;
import com.vincent.beauty_shop.request.product.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product createProduct(ProductCreateRequest productRequest);

    Product getProductById(Long id);

    Product updateProduct(Long id, ProductUpdateRequest productRequest);

    void deleteProduct(Long id);
}
