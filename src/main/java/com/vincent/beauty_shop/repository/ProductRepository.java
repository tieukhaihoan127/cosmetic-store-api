package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
