package com.micro.user.service.service;

import com.micro.user.service.entity.Hotel;
import com.micro.user.service.entity.Rating;
import com.micro.user.service.entity.User;
import com.micro.user.service.exception.ResourseNotFoundException;
import com.micro.user.service.externalService.HotelService;
import com.micro.user.service.externalService.RatingServiceClient;
import com.micro.user.service.repository.UserRepo;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingServiceClient ratingServiceClient;
    @Override
    public User save(User user) {
        return userRepo.save(user);
    }
    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }



    @Override
    public User findById(UUID userId) {
        User user = userRepo.findById(userId).orElseThrow(() ->
                new ResourseNotFoundException("User with given id not found on server !! : " + userId));
        Rating[] ratingOfUser = ratingServiceClient.getRatingsByUser(user.getUserId());
        log.info("Ratings of user: {}", ratingOfUser);
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
        List<Rating> ratingList = ratings.stream() .map(rating -> {
                    Hotel hotel = hotelService.getHotel(rating.getHotelId());
                    rating.setHotel(hotel);
                    return rating;
                }).collect(Collectors.toList());
        user.setRating(ratingList);
        return user;
    }
}