package com.vincent.beauty_shop.service.product;

import com.vincent.beauty_shop.entity.*;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.mapper.ProductMapper;
import com.vincent.beauty_shop.repository.*;
import com.vincent.beauty_shop.request.product.ProductCreateRequest;
import com.vincent.beauty_shop.request.product.ProductUpdateRequest;
import com.vincent.beauty_shop.request.product.ProductVariantCreateRequest;
import com.vincent.beauty_shop.request.product.ProductVariantUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;
    private ProductVariantRepository productVariantRepository;
    private ProductImageRepository productImageRepository;
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, BrandRepository brandRepository, CategoryRepository categoryRepository, ProductVariantRepository productVariantRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.productVariantRepository = productVariantRepository;
        this.productImageRepository = productImageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductCreateRequest productRequest) {
        Brand brand = null;

        if(productRequest.getBrandId() != null) {
            brand = brandRepository.findById(productRequest.getBrandId()).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        }

        Product product = Product.builder().title(productRequest.getTitle()).description(productRequest.getDescription()).origin(productRequest.getOrigin()).isLuxury(productRequest.isLuxury()).isFreeship(productRequest.isFreeship()).thumbnail(productRequest.getThumbnail()).brand(brand).build();

        if (productRequest.getCategoryIds() != null && !productRequest.getCategoryIds().isEmpty()) {
            List<Category> categories = categoryRepository.findAllById(productRequest.getCategoryIds());
            if (categories.size() != productRequest.getCategoryIds().size()) {
                throw new ResourceNotFoundException("One or more categories not found");
            }

            for(Category category : categories) {
                product.getCategories().add(category);
            }
        }

        if (productRequest.getVariants() != null && !productRequest.getVariants().isEmpty()) {
            for (ProductVariantCreateRequest req : productRequest.getVariants()) {
                ProductVariant variant = ProductVariant.builder()
                        .price(req.getPrice())
                        .sku(req.getSku())
                        .color(req.getColor())
                        .discountPercentage(req.getDiscountPercentage())
                        .product(product)
                        .build();

                if (req.getImages() != null && !req.getImages().isEmpty()) {
                    for (String url : req.getImages()) {
                        ProductImage img = ProductImage.builder()
                                .image(url)
                                .isThumbnail(false)
                                .productVariant(variant)
                                .build();
                        variant.getImages().add(img);
                    }
                }

                product.getProductVariants().add(variant);
            }
        }

        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    @Override
    public Product updateProduct(Long id, ProductUpdateRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        productMapper.updateProductFromDto(productRequest,product);

        if(productRequest.getBrandId() != null) {
            Brand brand = brandRepository.findById(productRequest.getBrandId()).orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
            product.setBrand(brand);
        }

        if (productRequest.getCategoryIds() != null && !productRequest.getCategoryIds().isEmpty()) {
            Set<Category> categories = new HashSet<>();
            for(Long catId : productRequest.getCategoryIds()) {
                Category category = categoryRepository.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
                categories.add(category);
            }

            product.setCategories(categories);
        }

        if (productRequest.getVariants() != null && !productRequest.getVariants().isEmpty()) {
            Map<String, ProductVariant> existingVariants = product.getProductVariants()
                    .stream()
                    .collect(Collectors.toMap(ProductVariant::getSku, v -> v));

            Set<ProductVariant> updatedVariants = new HashSet<>();

            for (ProductVariantUpdateRequest reqVariant : productRequest.getVariants()) {
                ProductVariant variant = existingVariants.get(reqVariant.getSku());

                if (variant != null) {

                    variant.setPrice(reqVariant.getPrice());
                    variant.setDiscountPercentage(reqVariant.getDiscountPercentage());
                    variant.setColor(reqVariant.getColor());


                    variant.getImages().clear();
                    for (String url : reqVariant.getImages()) {
                        ProductImage image = ProductImage.builder()
                                .image(url)
                                .isThumbnail(false)
                                .productVariant(variant)
                                .build();
                        variant.getImages().add(image);
                    }
                } else {

                    variant = ProductVariant.builder()
                            .sku(reqVariant.getSku())
                            .price(reqVariant.getPrice())
                            .discountPercentage(reqVariant.getDiscountPercentage())
                            .color(reqVariant.getColor())
                            .product(product)
                            .build();

                    for (String url : reqVariant.getImages()) {
                        ProductImage image = ProductImage.builder()
                                .image(url)
                                .isThumbnail(false)
                                .productVariant(variant)
                                .build();
                        variant.getImages().add(image);
                    }
                }
                updatedVariants.add(variant);
            }

            product.getProductVariants().clear();
            product.getProductVariants().addAll(updatedVariants);
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        for(Category cat : product.getCategories()) {
            cat.getProducts().remove(product);
        }

        product.getCategories().clear();

        productRepository.delete(product);
    }
}
