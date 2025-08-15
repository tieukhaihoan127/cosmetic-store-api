package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
}
