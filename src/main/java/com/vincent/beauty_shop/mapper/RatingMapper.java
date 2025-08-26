package com.vincent.beauty_shop.mapper;

import com.vincent.beauty_shop.entity.Rating;
import com.vincent.beauty_shop.request.rating.RatingUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRatingFromDto(RatingUpdateRequest dto, @MappingTarget Rating entity);
}
