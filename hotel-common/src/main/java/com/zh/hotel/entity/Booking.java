package com.zh.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName
public class Booking {
    @TableId
    private String bookingId;
    private String userId;
    private String roomId;
    private String hotelId;

    private LocalDate bookingDate;
    private LocalDate checkOutDate;
    private Integer amount;
    private PaymentStatus paymentStatus;
    private BookingStatus bookingStatus;
}
