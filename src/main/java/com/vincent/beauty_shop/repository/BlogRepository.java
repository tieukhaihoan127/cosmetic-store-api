package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long> {
}
