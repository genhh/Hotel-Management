package com.zh.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.hotel.entity.Rating;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingMapper extends BaseMapper<Rating> {

    public List<Rating> findByUserId(String userId);
    public List<Rating> findByHotelId(String hotelId);
}
