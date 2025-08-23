package com.vincent.beauty_shop.mapper;
import com.vincent.beauty_shop.entity.Product;
import com.vincent.beauty_shop.request.product.ProductUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductUpdateRequest dto, @MappingTarget Product entity);
}
