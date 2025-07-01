package com.ratingservice.service.impl;

import com.ratingservice.exception.ResourceNotFoundException;
import com.ratingservice.model.Rating;
import com.ratingservice.payload.RatingDto;
import com.ratingservice.repository.RatingRepository;
import com.ratingservice.serviceinterface.RatingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {


    private RatingRepository ratingRepository;

    private ModelMapper modelMapper;


    //create rating
    @Override
    public RatingDto createRating(RatingDto ratingDto) {
        Rating rating = modelMapper.map(ratingDto,Rating.class);
        Rating saveRating = ratingRepository.save(rating);
        return modelMapper.map(saveRating,RatingDto.class);
    }

    //get all rating
    @Override
    public List<RatingDto> getAllRating() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings.stream().map(rating-> modelMapper.map(rating,RatingDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> getRatingByUserId(Long userId) {
        List<Rating> ratings = ratingRepository.findByUserId(userId);

        return ratings.stream().map(rating -> modelMapper.map(rating,RatingDto.class)).collect(Collectors.toList());
    }
}
