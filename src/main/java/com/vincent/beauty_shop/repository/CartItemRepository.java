package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
