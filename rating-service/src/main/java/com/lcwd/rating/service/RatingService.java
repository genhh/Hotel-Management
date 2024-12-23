package com.lcwd.rating.service;

import com.zh.hotel.entity.Rating;

import java.util.List;

public interface RatingService {

    public Rating addRating(Rating rating);

    public List<Rating> getRatingAll();

    public List<Rating> getRatingByUserId(String userId);

    public List<Rating> getRatingByHotelId(String hotelId);

}
