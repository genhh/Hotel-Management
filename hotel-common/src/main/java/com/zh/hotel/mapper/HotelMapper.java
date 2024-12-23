package com.zh.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.hotel.entity.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelMapper extends BaseMapper<Hotel> {
    public List<Hotel> findByLocation(String location);
    public Hotel findByName(String name);
}
