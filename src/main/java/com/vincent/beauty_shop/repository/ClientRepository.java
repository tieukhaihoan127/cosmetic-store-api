package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
