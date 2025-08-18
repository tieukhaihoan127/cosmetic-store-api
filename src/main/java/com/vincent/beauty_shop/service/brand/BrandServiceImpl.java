package com.vincent.beauty_shop.service.brand;

import com.vincent.beauty_shop.entity.Brand;
import com.vincent.beauty_shop.entity.Category;
import com.vincent.beauty_shop.exception.DeleteForeignKeyException;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.mapper.BrandMapper;
import com.vincent.beauty_shop.repository.BrandRepository;
import com.vincent.beauty_shop.request.brand.BrandCreateRequest;
import com.vincent.beauty_shop.request.brand.BrandUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService{
    private BrandRepository brandRepository;
    private BrandMapper brandMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand createBrand(BrandCreateRequest brandRequest) {
        Brand brand = Brand.builder().title(brandRequest.getTitle()).logo(brandRequest.getLogo()).bannerImage(brandRequest.getBannerImage()).description(brandRequest.getDescription()).build();
        return brandRepository.save(brand);
    }

    @Override
    @Transactional(readOnly = true)
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id));
    }

    @Override
    public Brand updateBrand(Long id, BrandUpdateRequest brandRequest) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id));
        brandMapper.updateBrandFromDto(brandRequest, brand);
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + id));

        if (!brand.getProducts().isEmpty()) {
            throw new DeleteForeignKeyException("Cannot delete brand because it has products.");
        }

        brandRepository.delete(brand);
    }
}
