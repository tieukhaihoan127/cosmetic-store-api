package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Product;
import com.vincent.beauty_shop.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    boolean existsByClientIdAndProductId(Long clientId, Long productId);

    Optional<Wishlist> findByClientIdAndProductId(Long clientId, Long productId);

    @Query("SELECT w.product FROM Wishlist w WHERE w.client.id= :clientId")
    List<Product> findProductsByClientId(@Param("clientId") Long clientId);
}
