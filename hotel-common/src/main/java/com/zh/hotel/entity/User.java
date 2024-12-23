package com.zh.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableId
    private String userId;
    private String name;
    private String email;
    private String about;
    @JsonIgnore
    private List<Rating> ratingId;
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();
    @JsonIgnore
    private Wallet wallet;
}
