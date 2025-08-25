package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.ClientAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientAddressRepository extends JpaRepository<ClientAddress,Long> {
    List<ClientAddress> findByClientIdOrderByIdDesc(Long clientId);
}
