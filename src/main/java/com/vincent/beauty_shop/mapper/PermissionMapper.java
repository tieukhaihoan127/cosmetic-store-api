package com.vincent.beauty_shop.mapper;

import com.vincent.beauty_shop.entity.Permission;
import com.vincent.beauty_shop.request.permission.PermissionUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePermissionFromDto(PermissionUpdateRequest dto, @MappingTarget Permission entity);
}
