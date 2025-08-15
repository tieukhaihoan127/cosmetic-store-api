package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction,Long> {
}
