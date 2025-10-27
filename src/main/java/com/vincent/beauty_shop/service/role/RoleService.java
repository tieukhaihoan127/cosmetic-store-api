package com.vincent.beauty_shop.service.role;

import com.vincent.beauty_shop.entity.Role;
import com.vincent.beauty_shop.request.role.RoleCreateRequest;
import com.vincent.beauty_shop.request.role.RoleUpdateRequest;
import com.vincent.beauty_shop.response.authentication.RoleDTO;
import com.vincent.beauty_shop.response.authentication.RoleDetailDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();

    Role createRole(RoleCreateRequest roleCreateRequest);

    RoleDetailDTO getRoleById(Long id);

    Role updateRole(Long id, RoleUpdateRequest roleUpdateRequest);

    void deleteRole(Long id);
}
