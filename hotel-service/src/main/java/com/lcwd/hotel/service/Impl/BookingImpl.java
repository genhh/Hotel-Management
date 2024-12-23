package com.lcwd.hotel.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcwd.hotel.exception.NotFoundException;

import com.lcwd.hotel.service.BookingService;
import com.zh.hotel.entity.*;
import com.zh.hotel.mapper.BookingMapper;
import com.zh.hotel.mapper.RoomMapper;
import com.zh.hotel.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingImpl implements BookingService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public String bookRoom(String hotelId, String userId, Booking book) {

        Hotel hotel = hotelMapper.selectById(hotelId);
        if(hotel==null)
            throw  new NotFoundException("Hotel not found with id: " + hotelId);

        Room room = roomMapper.selectById(book.getRoomId());

        if(room==null)
            throw new NotFoundException("Room not found with id: " + book.getRoomId());

        if(room.getStatus().equals(Status.BOOKED)) {
            return "ROOM ALREADY BOOKED";
        }

        Booking booking = new Booking();
        booking.setHotelId(hotelId);
        booking.setRoomId(book.getRoomId());
        booking.setBookingId(book.getBookingId());
        booking.setAmount(book.getAmount());
        booking.setPaymentStatus(book.getPaymentStatus());
        booking.setBookingDate(book.getBookingDate());
        booking.setCheckOutDate(book.getCheckOutDate());
        booking.setUserId(userId);

        room.setStatus(Status.BOOKED);
        bookingMapper.insert(booking);
        return "BOOKED";

    }

    @Override
    public Booking getBookingById(String bookingId) {
        Booking booking = bookingMapper.selectById(bookingId);
        if(booking==null)throw new NotFoundException("Booking not found with id: " + bookingId + "");
        return booking;
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public String cancelBooking(String bookingId) {
        Booking booking = bookingMapper.selectById(bookingId);
        if(booking==null)
            throw new NotFoundException("Booking not found with id: " + bookingId + "");

        Room room = roomMapper.selectById(booking.getRoomId());
        if(room==null)throw  new NotFoundException("Room not found with id: " + booking.getRoomId() );

        if (room.getStatus().equals(Status.AVAILABLE)) {
            return "ROOM ALREADY AVAILABLE";
        }
        room.setStatus(Status.AVAILABLE);
        roomMapper.insert(room);
        return "CANCELLED";
    }

    @Override
    public String completeBooking(String bookingId) {
        Booking booking = bookingMapper.selectById(bookingId);
        if(booking==null)
            throw new NotFoundException("Booking not found with id: " + bookingId + "");

        Room room = roomMapper.selectById(booking.getRoomId());
        if(room==null)throw  new NotFoundException("Room not found with id: " + booking.getRoomId() );

        room.setStatus(Status.AVAILABLE);
        roomMapper.insert(room);
        return "COMPLETED";
    }


}
