package com.lcwd.hotel.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.hotel.entity.Hotel;
import com.zh.hotel.entity.Room;
import com.zh.hotel.entity.Status;
import com.lcwd.hotel.exception.NotFoundException;
import com.zh.hotel.mapper.BookingMapper;
import com.zh.hotel.mapper.HotelMapper;
import com.zh.hotel.mapper.RoomMapper;
import com.lcwd.hotel.service.hotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class hotelServiceImpl implements hotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private BookingMapper bookingMapper;


    @Override
    public String addHotel(Hotel hotel) {
        String randomUserId = UUID.randomUUID().toString();
        hotel.setId(randomUserId);
        hotelMapper.insert(hotel);
        return "success";
    }


    @Override
    public Hotel getHotelById(String hotelId) {
        Hotel hotel = hotelMapper.selectById(hotelId);
        if(hotel==null)
            throw  new NotFoundException("Hotel not found with id: " + hotelId);
        return hotel;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Hotel deleteHotelById(String hotelId) {
        Hotel hotel = hotelMapper.selectById(hotelId);
        if(hotel==null)
            throw  new NotFoundException("Hotel not found with id: " + hotelId);
        return hotel;
    }

    @Override
    public Room addRoom(String hotelId, Room room) {
        Hotel hotel = hotelMapper.selectById(hotelId);
        if(hotel==null)
            throw  new NotFoundException("Hotel not found with id: " + hotelId);

        log.info("Hotel: " + hotel);
//        room.setRoomId(UUID.randomUUID().toString());
        //hotel.getRooms().add(room);
        room.setHotelId(hotelId);
        room.setStatus(Status.AVAILABLE);
        hotelMapper.insert(hotel);
        return room;
    }

    @Override
    public Room getRoomById(String roomId) {
        Room room = roomMapper.selectById(roomId);

        if(room==null)
            throw new NotFoundException("Room not found with id: " + roomId);
        return room;
    }

    @Override
    public List<Room> getAllAvailableRoom(String hotelId) {
        Hotel hotel = hotelMapper.selectById(hotelId);
        if(hotel==null)
            throw  new NotFoundException("Hotel not found with id: " + hotelId);

        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hotel_id",hotelId);
        List<Room> rooms = roomMapper.selectList(queryWrapper);

        List<Room> availableRooms = new ArrayList<>();
        for(Room room: rooms) {
            if(room == null) {
                continue;
            }
            if(room.getStatus().equals(Status.AVAILABLE)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    @Override
    public List<Room> getAllBookedRoom(String hotelId) {

        Hotel hotel = hotelMapper.selectById(hotelId);
        if(hotel==null)
            throw  new NotFoundException("Hotel not found with id: " + hotelId);

        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hotel_id",hotelId);
        List<Room> rooms = roomMapper.selectList(queryWrapper);
        return rooms.stream().filter(r -> r.getStatus().equals(Status.BOOKED)).toList();
    }

    @Override
    public List<Room> getAllRooms(String hotelId) {

        Hotel hotel = hotelMapper.selectById(hotelId);
        if(hotel==null)
            throw  new NotFoundException("Hotel not found with id: " + hotelId);
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hotel_id",hotelId);
        List<Room> rooms = roomMapper.selectList(queryWrapper);
        return rooms;
    }

    @Override
    public Room deleteRoomById(String roomId) {
        Room room = roomMapper.selectById(roomId);
        if(room==null)throw  new NotFoundException("Room not found with id: " + roomId );

        roomMapper.deleteById(roomId);
        return room;
    }

    @Override
    public List<Hotel> getHotelByLocation(String location) {
        List<Hotel> hotels = hotelMapper.findByLocation(location);
        if (hotels.isEmpty()) {
            throw new NotFoundException("Hotel not available with location: " + location + "");
        }
        return hotels;
    }

    @Override
    public Hotel getHotelByName(String name) {
        Hotel hotel = hotelMapper.findByName(name);
        if (hotel == null) {
            throw new NotFoundException("Hotel not available with name: " + name + "");
        }
        return hotel;
    }
}










