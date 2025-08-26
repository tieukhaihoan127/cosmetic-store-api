package com.vincent.beauty_shop.service.rating;

import com.vincent.beauty_shop.entity.Rating;
import com.vincent.beauty_shop.request.rating.RatingCreateRequest;
import com.vincent.beauty_shop.request.rating.RatingUpdateRequest;

import java.util.List;

public interface RatingService {
    List<Rating> getAllRatings();

    Rating createRating(RatingCreateRequest request);

    Rating getRatingById(Long id);

    Rating updateRating(Long id, RatingUpdateRequest request);

    void deleteRating(Long id);
}
