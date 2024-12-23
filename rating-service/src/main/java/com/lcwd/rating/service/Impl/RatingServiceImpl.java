package com.lcwd.rating.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.hotel.entity.Rating;
import com.zh.hotel.mapper.RatingMapper;
import com.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingMapper ratingMapper;


    @Override
    public Rating addRating(Rating rating) {
        String randomUserId = UUID.randomUUID().toString();
        rating.setRatingId(randomUserId);
        ratingMapper.insert(rating);
        return rating;
    }

    @Override
    public List<Rating> getRatingAll() {
        return ratingMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingMapper.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingMapper.findByHotelId(hotelId);
    }
}
