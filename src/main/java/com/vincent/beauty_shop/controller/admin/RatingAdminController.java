package com.vincent.beauty_shop.controller.admin;

import com.vincent.beauty_shop.entity.Rating;
import com.vincent.beauty_shop.request.rating.RatingCreateRequest;
import com.vincent.beauty_shop.request.rating.RatingUpdateRequest;
import com.vincent.beauty_shop.service.rating.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/admin/ratings")
public class RatingAdminController {

    public RatingService ratingService;

    public RatingAdminController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return new ResponseEntity<>(ratingService.getAllRatings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRating(@PathVariable Long id) {
        return new ResponseEntity<>(ratingService.getRatingById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@Valid @RequestBody RatingCreateRequest request) {
        Rating rating = ratingService.createRating(request);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody RatingUpdateRequest request) {
        Rating rating = ratingService.updateRating(id, request);
        return new ResponseEntity<>(rating,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.ok("Rating successfully deleted");
    }

}
