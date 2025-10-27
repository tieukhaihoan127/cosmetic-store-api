package com.vincent.beauty_shop.response.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDetailDTO {
    private Long id;

    private String title;

    private String description;

    private List<RoleDTO> roles;
}
