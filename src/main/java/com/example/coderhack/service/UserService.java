package com.example.coderhack.service;

import java.util.List;
import java.util.Optional;

import com.example.coderhack.dto.ScoreRequestDto;
import com.example.coderhack.dto.UserRequestDto;
import com.example.coderhack.entity.UserEntity;

public interface UserService {
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUserById(String id);
    String addUser(UserRequestDto userRequestDTO);
    String updateUserById(String id, ScoreRequestDto scoreRequestDto) throws Exception;
    String deleteUserById(String id);
}
