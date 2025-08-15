package com.vincent.beauty_shop.repository;

import com.vincent.beauty_shop.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
