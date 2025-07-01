package com.userservice.controller;

import com.userservice.payload.UserDto;
import com.userservice.serviceinterface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    //create user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    //get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

    //get all user
    @GetMapping
    public ResponseEntity<List<UserDto>> getUserById() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    //update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto update) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId,update));
    }

    //delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delateUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User delated successfully!");
    }

}
