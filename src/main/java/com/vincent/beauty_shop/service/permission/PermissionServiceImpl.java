package com.vincent.beauty_shop.service.permission;

import com.vincent.beauty_shop.entity.Permission;
import com.vincent.beauty_shop.entity.Role;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.mapper.PermissionMapper;
import com.vincent.beauty_shop.repository.PermissionRepository;
import com.vincent.beauty_shop.request.permission.PermissionCreateRequest;
import com.vincent.beauty_shop.request.permission.PermissionUpdateRequest;
import com.vincent.beauty_shop.response.authentication.PermissionDTO;
import com.vincent.beauty_shop.response.authentication.PermissionDetailDTO;
import com.vincent.beauty_shop.response.authentication.RoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private PermissionRepository permissionRepository;
    private PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
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
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
        List<RoleDTO> roleDTOList = mapToRoleDTO(permission);

        return PermissionDetailDTO.builder().title(permission.getTitle()).description(permission.getDescription()).roles(roleDTOList).build();
    }

    @Override
    public Permission updatePermission(Long id, PermissionUpdateRequest request) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
        permissionMapper.updatePermissionFromDto(request,permission);
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
        permissionRepository.delete(permission);
    }

    private List<RoleDTO> mapToRoleDTO(Permission permission) {
        Set<Role> roles = permission.getRoles();
        List<RoleDTO> roleDTOList = new ArrayList<>();

        for(Role role : roles){
            RoleDTO roleDTO = RoleDTO.builder().title(role.getTitle()).description(role.getDescription()).build();
            roleDTOList.add(roleDTO);
        }

        return roleDTOList;
    }

    private PermissionDTO mapToPermissionDTO(Permission permission) {
        PermissionDTO permissionDTO = PermissionDTO.builder().title(permission.getTitle()).description(permission.getDescription()).build();
        return permissionDTO;
    }
}
