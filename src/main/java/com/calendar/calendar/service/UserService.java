package com.calendar.calendar.service;

import com.calendar.calendar.model.User;

import java.util.List;


public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUser();
    User updateUserFromId(Long id,User user);
    void deleteUserFromId(Long id);
}
