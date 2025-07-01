package com.ratingservice.serviceinterface;

import com.ratingservice.payload.RatingDto;

import java.util.List;

public interface RatingService {

    //create rating
    RatingDto createRating(RatingDto ratingDto);

    //get all rating
    List<RatingDto> getAllRating();

    //get rating by user id
    List<RatingDto> getRatingByUserId(Long userId);



}
