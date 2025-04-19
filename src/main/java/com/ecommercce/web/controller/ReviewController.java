package com.ecommercce.web.controller;

import com.ecommercce.persitence.entity.ReviewEntity;
import com.ecommercce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * obtiene todas las reseñas aprobadas de un producto específico
     * @param productId id del producto
     * @return lista de reseñas aprobadas
     */
    @GetMapping("/approved/{productId}")
    public ResponseEntity<List<ReviewEntity>> getApprovedReviews(@PathVariable int productId) {
        return ResponseEntity.ok(this.reviewService.getApprovedReviewsByProductId(productId));
    }
}

