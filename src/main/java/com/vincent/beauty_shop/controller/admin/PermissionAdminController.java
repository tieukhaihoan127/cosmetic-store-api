package com.vincent.beauty_shop.controller.admin;

import com.vincent.beauty_shop.entity.Permission;
import com.vincent.beauty_shop.request.permission.PermissionCreateRequest;
import com.vincent.beauty_shop.request.permission.PermissionUpdateRequest;
import com.vincent.beauty_shop.response.authentication.PermissionDTO;
import com.vincent.beauty_shop.response.authentication.PermissionDetailDTO;
import com.vincent.beauty_shop.service.permission.PermissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/admin/permissions")
public class PermissionAdminController {

    private PermissionService permissionService;

    public PermissionAdminController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> getAllPermissions() {
        return new ResponseEntity<>(permissionService.getAllPermissions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDetailDTO> getPermission(@PathVariable Long id) {
        PermissionDetailDTO permission = permissionService.getPermissionById(id);
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Permission> addPermission(@Valid @RequestBody PermissionCreateRequest request) {
        Permission permission = permissionService.createPermission(request);
        return new ResponseEntity<>(permission, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permission> updatePermission(@PathVariable Long id,@Valid @RequestBody PermissionUpdateRequest request) {
        Permission updatedPermission = permissionService.updatePermission(id, request);
        return new ResponseEntity<>(updatedPermission, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok("Permission successfully deleted");
    }

}
