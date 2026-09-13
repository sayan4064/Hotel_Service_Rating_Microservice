package com.micro.rating.Service;

import com.micro.rating.Entity.Rating;
import com.micro.rating.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatingsByUserId(UUID userId) {
        return ratingRepository.getRatingsByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(UUID hotelId) {
        return ratingRepository.getRatingsByHotelId(hotelId);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}
