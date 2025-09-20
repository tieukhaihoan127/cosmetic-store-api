package com.vincent.beauty_shop.service.permission;

import com.vincent.beauty_shop.entity.Permission;
import com.vincent.beauty_shop.request.permission.PermissionCreateRequest;
import com.vincent.beauty_shop.request.permission.PermissionUpdateRequest;

import java.util.List;

public interface PermissionService {
    List<Permission> getAllPermissions();

    Permission createPermission(PermissionCreateRequest request);

    Permission getPermissionById(Long id);

    Permission updatePermission(Long id, PermissionUpdateRequest request);

    void deletePermission(Long id);
}
