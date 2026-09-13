package com.micro.user.service.externalService;

import com.micro.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name="RATINGSERVICE")
public interface RatingService {

    @PostMapping("/ratings")
    public ResponseEntity<Rating> createRating(Rating rating);


    @PutMapping("/rating/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, @RequestBody Rating rating);

    @DeleteMapping("/rating/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") String ratingId);
}
