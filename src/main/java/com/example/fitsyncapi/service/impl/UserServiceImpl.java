package com.example.fitsyncapi.service.impl;

import com.example.fitsyncapi.dto.UserUpdateDTO;
import com.example.fitsyncapi.model.User;
import com.example.fitsyncapi.repository.UserRepository;
import com.example.fitsyncapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Integer id, User updatedUser) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedUser.getName());
                    existing.setEmail(updatedUser.getEmail());
                    existing.setPassword(updatedUser.getPassword());
                    existing.setAge(updatedUser.getAge());
                    existing.setGender(updatedUser.getGender());
                    existing.setWeight(updatedUser.getWeight());
                    existing.setHeight(updatedUser.getHeight());
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserFields(Integer id, UserUpdateDTO dto) {
        return userRepository.findById(id)
                .map(user -> {
                    if (dto.getName() != null && !dto.getName().isBlank()) user.setName(dto.getName());
                    if (dto.getPassword() != null && !dto.getPassword().isBlank()) user.setPassword(dto.getPassword());
                    if (dto.getAge() != null) user.setAge(dto.getAge());
                    if (dto.getGender() != null) user.setGender(dto.getGender());
                    if (dto.getWeight() != null) user.setWeight(dto.getWeight());
                    if (dto.getHeight() != null) user.setHeight(dto.getHeight());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
