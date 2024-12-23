package com.zh.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.hotel.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    public User findByEmail(String email);
}
