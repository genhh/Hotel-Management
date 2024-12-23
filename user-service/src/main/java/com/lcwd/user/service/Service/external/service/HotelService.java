package com.lcwd.user.service.Service.external.service;


import com.zh.hotel.entity.Booking;
import com.zh.hotel.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "HOTELS-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId);


    @PostMapping("/hotels/addBooking")
    public Boolean addBookingFromHotel(@RequestBody Booking booking);

}
