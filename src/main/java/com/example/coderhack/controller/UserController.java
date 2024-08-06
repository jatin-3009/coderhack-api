package com.example.coderhack.controller;

import java.util.List;
import java.util.Optional;

import com.example.coderhack.dto.ScoreRequestDto;
import com.example.coderhack.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.coderhack.entity.UserEntity;
import com.example.coderhack.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
public class UserController {
    private static final String USER_API_ENDPOINT = "/users";

    @Autowired
    UserService userService;

    @GetMapping(USER_API_ENDPOINT)
    public ResponseEntity<List<UserEntity>> users() {
        List<UserEntity> userEntities = userService.getAllUsers();

        if (!userEntities.isEmpty()) {
            return ResponseEntity.ok(userEntities);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(USER_API_ENDPOINT + "/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") String id) {
        Optional<UserEntity> optionalUser = userService.getUserById(id);

        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(USER_API_ENDPOINT)
    public ResponseEntity<String> user(@Valid @RequestBody UserRequestDto userRequestDTO) {
        String createdUserId = userService.addUser(userRequestDTO);
        return ResponseEntity.ok(createdUserId);
    }

    @PutMapping(USER_API_ENDPOINT + "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") String id, @Valid @RequestBody ScoreRequestDto scoreRequestDto) throws Exception {
        String updateUserId = userService.updateUserById(id, scoreRequestDto);
        return ResponseEntity.ok(updateUserId);
    }

    @DeleteMapping(USER_API_ENDPOINT + "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        String deletedUserId = userService.deleteUserById(id);
        return ResponseEntity.ok(deletedUserId);
    }
}
