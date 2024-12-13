package com.cityClass.CityClass.Service;

import com.cityClass.CityClass.Model.Review;
import com.cityClass.CityClass.Model.User;
import com.cityClass.CityClass.Repo.ReviewRepository;
import com.cityClass.CityClass.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> createReview(Long id,String reviewConten) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Review review = new Review();
        review.setName(user.getUser_name());
        review.setReview(reviewConten);
        review.setUser(user);
        repository.save(review);
        return ResponseEntity.ok("Отзыв пользователя "+id+ " добавлен в бд");
    }
}
