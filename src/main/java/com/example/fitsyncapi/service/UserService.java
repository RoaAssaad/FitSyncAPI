package com.example.fitsyncapi.service;

import com.example.fitsyncapi.dto.UserUpdateDTO;
import com.example.fitsyncapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Integer id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);

    // Update specific profile fields (e.g. name, weight)
    User updateUserFields(Integer id, UserUpdateDTO dto);
}
