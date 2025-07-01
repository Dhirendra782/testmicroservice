package com.userservice.serviceinterface;

import com.userservice.payload.UserDto;

import java.util.List;

public interface UserService {

    //Create user
    UserDto createUser(UserDto userDto);

    //Get user by id
    UserDto getUserById(Long userId);

    //Get all user
    List<UserDto> getAllUser();

    //Update user
    UserDto updateUser(Long userId, UserDto update);

    //Delate user
    void deleteUser(Long userId);


}
