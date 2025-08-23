package com.vincent.beauty_shop.mapper;

import com.vincent.beauty_shop.entity.Client;
import com.vincent.beauty_shop.request.client.ClientUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClientFromDto(ClientUpdateRequest dto, @MappingTarget Client entity);
}
