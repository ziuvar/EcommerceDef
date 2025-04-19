package com.ecommercce.persitence.repository;

import com.ecommercce.persitence.entity.ReviewEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ReviewRepository extends ListCrudRepository<ReviewEntity, Long> {

    List<ReviewEntity> findAllByProductIdProductAndApprovedTrue(Integer productId);
}