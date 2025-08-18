package com.vincent.beauty_shop.controller.admin;

import com.vincent.beauty_shop.entity.Brand;
import com.vincent.beauty_shop.request.brand.BrandCreateRequest;
import com.vincent.beauty_shop.request.brand.BrandUpdateRequest;
import com.vincent.beauty_shop.service.brand.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    private BrandService brandService;

    public BrandController(BrandService brandService){
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

    @PostMapping
    public ResponseEntity<Brand> addBrand(@RequestBody BrandCreateRequest request){
        Brand brand = brandService.createBrand(request);
        return new ResponseEntity<>(brand, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id,@RequestBody BrandUpdateRequest request) {
        Brand updateBrand = brandService.updateBrand(id, request);
        return new ResponseEntity<>(updateBrand, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok("Brand successfully deleted");
    }

}
