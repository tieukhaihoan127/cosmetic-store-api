package com.vincent.beauty_shop.service.role;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vincent.beauty_shop.entity.Permission;
import com.vincent.beauty_shop.entity.Role;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.mapper.RoleMapper;
import com.vincent.beauty_shop.repository.PermissionRepository;
import com.vincent.beauty_shop.repository.RoleRepository;
import com.vincent.beauty_shop.request.role.RoleCreateRequest;
import com.vincent.beauty_shop.request.role.RoleUpdateRequest;
import com.vincent.beauty_shop.response.authentication.PermissionDTO;
import com.vincent.beauty_shop.response.authentication.RoleDTO;
import com.vincent.beauty_shop.response.authentication.RoleDetailDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    private PermissionRepository permissionRepository;

    private RoleMapper roleMapper;


    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(this::mapToRoleDTO).collect(Collectors.toList());
    }

    @Override
    public Role createRole(RoleCreateRequest roleCreateRequest) {

        Role role = Role.builder().title(roleCreateRequest.getTitle()).description(roleCreateRequest.getDescription()).build();

        if (roleCreateRequest.getPermissionIds() != null && !roleCreateRequest.getPermissionIds().isEmpty()) {
            List<Permission> permissions = permissionRepository.findAllById(roleCreateRequest.getPermissionIds());
            if (permissions.size() != roleCreateRequest.getPermissionIds().size()) {
                throw new ResourceNotFoundException("Some permissions not found");
            }

            role.setPermissions(new HashSet<>(permissions));
        }

        return roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDetailDTO getRoleById(Long id) {
        List<PermissionDTO> permissions = List.of();
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        permissions = mapToPermissionDTOs(role.getPermissions());

        return RoleDetailDTO.builder()
                .id(id)
                .title(role.getTitle())
                .description(role.getDescription())
                .permissions(permissions)
                .build();
    }

    @Override
    public Role updateRole(Long id, RoleUpdateRequest roleUpdateRequest) {
        Role role = roleRepository.findWithPermissionsById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        Set<Permission> permissions = new HashSet<>();
        roleMapper.updateRoleFromDto(roleUpdateRequest, role);

        if(!roleUpdateRequest.getPermissionIds().isEmpty()) {
            permissions.addAll(permissionRepository.findAllById(roleUpdateRequest.getPermissionIds()));
        }
        role.setPermissions(permissions);

        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
        roleRepository.delete(role);
    }

    private List<PermissionDTO> mapToPermissionDTOs(Set<Permission> permissions) {
        return permissions.stream()
                .sorted(Comparator.comparing(Permission::getId))
                .map(p -> PermissionDTO.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .description(p.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
    private RoleDTO mapToRoleDTO(Role role) {
        RoleDTO roleDTO = RoleDTO.builder().id(role.getId()).title(role.getTitle()).description(role.getDescription()).build();
        return roleDTO;
    }
}
