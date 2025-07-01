package com.userservice.external.client;


import com.userservice.model.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name ="RATING-SERVICE")
public interface RatingServiceClient {

    @GetMapping("/ratings/users/{userId}")
    List<Rating> getRating(@PathVariable("userId") Long userId);

}
