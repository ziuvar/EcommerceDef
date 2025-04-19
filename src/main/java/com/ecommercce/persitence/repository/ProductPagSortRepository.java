package com.ecommercce.persitence.repository;

import com.ecommercce.persitence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ProductPagSortRepository extends ListPagingAndSortingRepository<ProductEntity, Integer> {
    Page<ProductEntity> findByAvailableTrue(Pageable pageable);
}
