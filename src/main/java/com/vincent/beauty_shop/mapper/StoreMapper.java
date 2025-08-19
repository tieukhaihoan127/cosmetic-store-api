package com.vincent.beauty_shop.mapper;

import com.vincent.beauty_shop.entity.Store;
import com.vincent.beauty_shop.request.store.StoreUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStoreFromDto(StoreUpdateRequest dto, @MappingTarget Store entity);
}