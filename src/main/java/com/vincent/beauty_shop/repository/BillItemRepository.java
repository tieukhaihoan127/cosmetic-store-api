package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillItemRepository extends JpaRepository<BillItem,Long> {
}
