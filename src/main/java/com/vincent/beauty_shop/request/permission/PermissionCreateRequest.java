package com.vincent.beauty_shop.request.permission;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionCreateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
