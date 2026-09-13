package com.micro.user.service.externalService;

import com.micro.user.service.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class RatingServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Retryable(
            retryFor= RuntimeException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public Rating[] getRatingsByUser(UUID userId){
        return restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + userId, Rating[].class);
    }
}
