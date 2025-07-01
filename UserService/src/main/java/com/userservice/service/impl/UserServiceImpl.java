package com.userservice.service.impl;

import com.userservice.exception.ResourceNotFoundException;
import com.userservice.external.client.RatingServiceClient;
import com.userservice.model.Rating;
import com.userservice.model.User;
import com.userservice.payload.UserDto;
import com.userservice.repository.UserRepository;
import com.userservice.serviceinterface.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private RatingServiceClient ratingServiceClient;

    //create user
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User saveUser = userRepository.save(user);
        return modelMapper.map(saveUser,UserDto.class);
    }

    //get by user id
    @Override
    public UserDto getUserById(Long userId) {
        //fetch user db
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        // Fetch ratings from Rating microservice
        List<Rating> ratings = ratingServiceClient.getRating(userId);

        // Map user to DTO
        UserDto userDto = modelMapper.map(user,UserDto.class);

        //Set ratings in the DTO
        userDto.setRatings(ratings);

        return userDto;
    }

    //get All user
    @Override
    public List<UserDto> getAllUser() {
        //fetch all user from db
        List<User> users = userRepository.findAll();

        return users.stream().map(user-> {
            //Map User to UserDto
            UserDto userDto = modelMapper.map(user,UserDto.class);

            //Calling Rating service for each user
            List<Rating> ratings = ratingServiceClient.getRating(user.getUserId());

            //Set rating service for each user
            userDto.setRatings(ratings);
            return userDto;

        }).collect(Collectors.toList());

    }

    //update user
    @Override
    public UserDto updateUser(Long userId, UserDto update) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        user.setName(update.getName());
        user.setEmail(update.getEmail());
        user.setAbout(update.getAbout());
        User updateUser = userRepository.save(user);
        return modelMapper.map(updateUser,UserDto.class);
    }

    //Delete user
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        userRepository.deleteById(userId);
    }

}
