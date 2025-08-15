package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
