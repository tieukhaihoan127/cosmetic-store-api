package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
}
