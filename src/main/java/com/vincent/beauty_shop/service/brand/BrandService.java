package com.vincent.beauty_shop.service.brand;

import com.vincent.beauty_shop.entity.Brand;
import com.vincent.beauty_shop.request.brand.BrandCreateRequest;
import com.vincent.beauty_shop.request.brand.BrandUpdateRequest;
import com.vincent.beauty_shop.request.category.CategoryCreateRequest;
import com.vincent.beauty_shop.request.category.CategoryUpdateRequest;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();

    Brand createBrand(BrandCreateRequest brandRequest);

    Brand getBrandById(Long id);

    Brand updateBrand(Long id, BrandUpdateRequest brandRequest);

    void deleteBrand(Long id);
}
