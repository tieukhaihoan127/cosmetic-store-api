package com.vincent.beauty_shop.controller.admin;

import com.vincent.beauty_shop.entity.Role;
import com.vincent.beauty_shop.request.role.RoleCreateRequest;
import com.vincent.beauty_shop.request.role.RoleUpdateRequest;
import com.vincent.beauty_shop.response.authentication.RoleDTO;
import com.vincent.beauty_shop.response.authentication.RoleDetailDTO;
import com.vincent.beauty_shop.service.role.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/admin/roles")
public class RoleAdminController {

    private RoleService roleService;

    public RoleAdminController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDetailDTO> getRole(@PathVariable Long id) {
        RoleDetailDTO role = roleService.getRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> addRole(@Valid @RequestBody RoleCreateRequest request) {
        Role role = roleService.createRole(request);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id,@Valid @RequestBody RoleUpdateRequest request) {
        Role role = roleService.updateRole(id, request);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role successfully deleted");
    }

}
