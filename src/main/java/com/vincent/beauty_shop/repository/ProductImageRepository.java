package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
}
