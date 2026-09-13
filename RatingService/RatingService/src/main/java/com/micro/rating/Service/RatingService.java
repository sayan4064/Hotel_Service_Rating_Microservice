package com.micro.rating.Service;

import com.micro.rating.Entity.Rating;

import java.util.List;
import java.util.UUID;

public interface RatingService {
    Rating createRating(Rating rating);
    List<Rating> getRatingsByUserId(UUID userId);
    List<Rating> getRatingsByHotelId(UUID hotelId);
    List<Rating> getAllRatings();
}
