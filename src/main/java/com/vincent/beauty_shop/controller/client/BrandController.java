package com.vincent.beauty_shop.controller.client;

import com.vincent.beauty_shop.entity.Brand;
import com.vincent.beauty_shop.service.brand.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.version}/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        return new ResponseEntity<>(brandService.getAllBrands(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrand(@PathVariable Long id) {
        Brand brand = brandService.getBrandById(id);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }
}
