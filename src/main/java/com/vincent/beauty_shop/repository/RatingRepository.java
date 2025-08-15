package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {
}
