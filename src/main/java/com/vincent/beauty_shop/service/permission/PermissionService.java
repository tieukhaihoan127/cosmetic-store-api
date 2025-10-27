package com.vincent.beauty_shop.service.permission;

import com.vincent.beauty_shop.entity.Permission;
import com.vincent.beauty_shop.request.permission.PermissionCreateRequest;
import com.vincent.beauty_shop.request.permission.PermissionUpdateRequest;
import com.vincent.beauty_shop.response.authentication.PermissionDTO;
import com.vincent.beauty_shop.response.authentication.PermissionDetailDTO;

import java.util.List;

public interface PermissionService {
    List<PermissionDTO> getAllPermissions();

    Permission createPermission(PermissionCreateRequest request);

    PermissionDetailDTO getPermissionById(Long id);

    Permission updatePermission(Long id, PermissionUpdateRequest request);

    void deletePermission(Long id);
}
