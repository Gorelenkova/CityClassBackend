package com.cityClass.CityClass.controller;

import com.cityClass.CityClass.DTO.ReviewDTO;
import com.cityClass.CityClass.Repo.ReviewRepository;
import com.cityClass.CityClass.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @PostMapping("/add")
    public ResponseEntity<String> createReview(@RequestParam Long id,@RequestParam String reviewConten){
        return service.createReview(id,reviewConten);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = service.getAllReviews();
        return ResponseEntity.ok(reviews);
    }
}
