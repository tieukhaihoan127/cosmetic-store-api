package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
