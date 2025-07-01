package com.ratingservice.repository;

import com.ratingservice.model.Rating;
import com.ratingservice.payload.RatingDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    //Custome finder method
    List<Rating> findByUserId(Long userId);

}
