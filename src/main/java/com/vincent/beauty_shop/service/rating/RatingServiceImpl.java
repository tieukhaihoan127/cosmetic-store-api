package com.vincent.beauty_shop.service.rating;

import com.vincent.beauty_shop.entity.Product;
import com.vincent.beauty_shop.entity.Rating;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.mapper.RatingMapper;
import com.vincent.beauty_shop.repository.ProductRepository;
import com.vincent.beauty_shop.repository.RatingRepository;
import com.vincent.beauty_shop.request.rating.RatingCreateRequest;
import com.vincent.beauty_shop.request.rating.RatingUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RatingServiceImpl implements RatingService{

    private RatingRepository ratingRepository;
    private ProductRepository productRepository;
    private RatingMapper ratingMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, ProductRepository productRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.productRepository = productRepository;
        this.ratingMapper = ratingMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating createRating(RatingCreateRequest request) {
        Product product = null;

        if(request.getProductId() != null) {
            product = productRepository.findById(request.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        }
        else {
            throw new IllegalArgumentException("ProductId is required when creating rating");
        }

        Rating rating = Rating.builder().image(request.getImage()).detailDesc(request.getDetailDesc()).shortDesc(request.getShortDesc()).email(request.getEmail()).nickname(request.getNickname()).star(request.getStar()).isReview(request.isReview()).product(product).build();
        return ratingRepository.save(rating);
    }

    @Override
    @Transactional(readOnly = true)
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating not found with id " + id));
    }

    @Override
    public Rating updateRating(Long id, RatingUpdateRequest request) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating not found with id " + id));
        ratingMapper.updateRatingFromDto(request, rating);
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating not found with id " + id));
        ratingRepository.delete(rating);
    }
}
