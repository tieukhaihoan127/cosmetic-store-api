package com.vincent.beauty_shop.mapper;


import com.vincent.beauty_shop.entity.Role;
import com.vincent.beauty_shop.request.role.RoleUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoleFromDto(RoleUpdateRequest dto, @MappingTarget Role entity);
}
