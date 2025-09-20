package com.vincent.beauty_shop.service.permission;

import com.vincent.beauty_shop.entity.Permission;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.mapper.PermissionMapper;
import com.vincent.beauty_shop.repository.PermissionRepository;
import com.vincent.beauty_shop.request.permission.PermissionCreateRequest;
import com.vincent.beauty_shop.request.permission.PermissionUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission createPermission(PermissionCreateRequest request) {
        Permission permission = Permission.builder().title(request.getTitle()).description(request.getDescription()).build();
        return permissionRepository.save(permission);
    }

    @Override
    @Transactional(readOnly = true)
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission not found with id " + id));
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
}
