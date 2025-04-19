package com.ecommercce.service;

import com.ecommercce.persitence.entity.ReviewEntity;
import com.ecommercce.persitence.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * obtiene todas las reseñas aprobadas de un producto específico
     * @param productId id del producto
     * @return lista de reseñas aprobadas
     */
    public List<ReviewEntity> getApprovedReviewsByProductId(int productId) {
        return this.reviewRepository.findAllByProductIdProductAndApprovedTrue(productId);
    }

    /**
     * guarda una nueva reseña
     * @param review entidad reseña
     * @return reseña guardada
     */
    public ReviewEntity saveReview(ReviewEntity review) {
        return this.reviewRepository.save(review);
    }

    /**
     * elimina una reseña por su id
     * @param idReview id de la reseña
     */
    public void deleteReview(long idReview) {
        this.reviewRepository.deleteById(idReview);
    }

    /**
     * obtiene una reseña por su id
     * @param idReview id de la reseña
     * @return reseña encontrada o null
     */
    public ReviewEntity getReview(long idReview) {
        return this.reviewRepository.findById(idReview).orElse(null);
    }
}
