package com.vincent.beauty_shop.request.role;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreateRequest {

    @NotEmpty
    private String title;

    private String description;

    @NotEmpty(message = "Permission IDs cannot be empty")
    private List<Long > permissionIds;
}
