package com.lcwd.hotel.service;

import com.zh.hotel.entity.Hotel;
import com.zh.hotel.entity.Room;

import java.util.List;

public interface hotelService {
    public String addHotel(Hotel hotel);
    public Hotel getHotelById(String hotelId);
    public List<Hotel> getAllHotels();
    public Hotel deleteHotelById(String hotelId);
    public Room addRoom(String hotelId, Room room);
    public Room getRoomById(String roomId);
    public List<Room> getAllAvailableRoom(String hotelId);
    public List<Room> getAllBookedRoom(String hotelId);
    public List<Room> getAllRooms(String hotelId);
    public Room deleteRoomById(String roomId);
    public List<Hotel> getHotelByLocation(String location);
    public Hotel getHotelByName(String name);


}
