package com.vincent.beauty_shop.mapper;

import com.vincent.beauty_shop.entity.ClientAddress;
import com.vincent.beauty_shop.request.client.ClientAddressUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClientAddressFromDto(ClientAddressUpdateRequest dto, @MappingTarget ClientAddress entity);
}
