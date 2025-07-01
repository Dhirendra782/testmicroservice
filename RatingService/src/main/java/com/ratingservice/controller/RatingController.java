package com.ratingservice.controller;

import com.ratingservice.payload.RatingDto;
import com.ratingservice.serviceinterface.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
public class RatingController {

    private RatingService ratingService;


    //create rating
    @PostMapping
    public ResponseEntity<RatingDto> createRating(@RequestBody RatingDto ratingDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(ratingDto));
    }

    //get all rating
    @GetMapping
    public ResponseEntity<List<RatingDto>> getAllRating() {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRating());
    }

    //get rating by userId
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<RatingDto>> getRatingByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userId));
    }

}
