package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
