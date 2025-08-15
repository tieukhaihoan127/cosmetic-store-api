package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
