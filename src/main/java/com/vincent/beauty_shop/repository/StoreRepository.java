package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
