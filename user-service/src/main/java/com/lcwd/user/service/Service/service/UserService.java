package com.lcwd.user.service.Service.service;

import com.zh.hotel.entity.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);

    public List<User> getAllUsers();

    public User getUserById(String userId);

    public void deleteUserById(String userId);

    public User getUserByEmail(String email);
}
