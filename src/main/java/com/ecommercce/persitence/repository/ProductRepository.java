package com.ecommercce.persitence.repository;

import com.ecommercce.persitence.entity.ProductEntity;
import com.ecommercce.service.dto.UpdateProductPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends ListCrudRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByAvailableTrueOrderByPrice();
    Optional<ProductEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<ProductEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<ProductEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<ProductEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    int countByNoWoolTrue();

    @Query(value =
            "UPDATE product " +
            "SET price = :#{#newProductPrice.newPrice} " +
            "WHERE id_product = :#{#newProductPrice.productId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newProductPrice") UpdateProductPriceDto newProductPrice);
}
