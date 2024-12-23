package com.zh.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("hotel")
public class Hotel {
    @TableId
    private String id;
    private String name;
    private String location;
    private String about;

    //private List<Room> rooms;

    //private List<Booking> bookings;

}
