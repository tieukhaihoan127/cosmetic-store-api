package com.vincent.beauty_shop.service.permission;

import com.vincent.beauty_shop.entity.Permission;
import com.vincent.beauty_shop.entity.Role;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.mapper.PermissionMapper;
import com.vincent.beauty_shop.repository.PermissionRepository;
import com.vincent.beauty_shop.repository.RoleRepository;
import com.vincent.beauty_shop.request.permission.PermissionCreateRequest;
import com.vincent.beauty_shop.request.permission.PermissionUpdateRequest;
import com.vincent.beauty_shop.response.authentication.PermissionDTO;
import com.vincent.beauty_shop.response.authentication.PermissionDetailDTO;
import com.vincent.beauty_shop.response.authentication.RoleDTO;
import com.vincent.beauty_shop.response.authentication.RoleDetailDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private PermissionRepository permissionRepository;
    private PermissionMapper permissionMapper;
    private RoleRepository roleRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper, RoleRepository roleRepository) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll().stream().map(this::mapToPermissionDTO).collect(Collectors.toList());
    }

    @Override
    public Permission createPermission(PermissionCreateRequest request) {
        Permission permission = Permission.builder().title(request.getTitle()).description(request.getDescription()).build();
        return permissionRepository.save(permission);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionDetailDTO getPermissionById(Long id) {
        List<RoleDTO> roles = List.of();
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
        roles = mapToRoleDTOs(permission.getRoles());

        return PermissionDetailDTO.builder().id(id).title(permission.getTitle()).description(permission.getDescription()).roles(roles).build();
    }

    @Override
    public Permission updatePermission(Long id, PermissionUpdateRequest request) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
        Set<Role> roles = new HashSet<>();
        permissionMapper.updatePermissionFromDto(request,permission);

        if(request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            roles.addAll(roleRepository.findAllById(request.getRoleIds()));
            permission.setRoles(roles);
        }

        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
        permissionRepository.delete(permission);
    }

    private List<RoleDTO> mapToRoleDTOs(Set<Role> roles) {
        return roles.stream()
                .sorted(Comparator.comparing(Role::getId))
                .map(p -> RoleDTO.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .description(p.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    private PermissionDTO mapToPermissionDTO(Permission permission) {
        PermissionDTO permissionDTO = PermissionDTO.builder().id(permission.getId()).title(permission.getTitle()).description(permission.getDescription()).build();
        return permissionDTO;
    }
}
