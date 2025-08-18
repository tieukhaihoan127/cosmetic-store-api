package com.vincent.beauty_shop.mapper;

import com.vincent.beauty_shop.entity.Brand;
import com.vincent.beauty_shop.request.brand.BrandUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBrandFromDto(BrandUpdateRequest dto, @MappingTarget Brand entity);
}
