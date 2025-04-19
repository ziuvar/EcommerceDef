package com.ecommercce.service;

import com.ecommercce.persitence.entity.ProductEntity;
import com.ecommercce.persitence.repository.ProductPagSortRepository;
import com.ecommercce.persitence.repository.ProductRepository;
import com.ecommercce.service.dto.UpdateProductPriceDto;
import com.ecommercce.service.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductPagSortRepository productPagSortRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductPagSortRepository productPagSortRepository) {
        this.productRepository = productRepository;
        this.productPagSortRepository = productPagSortRepository;
    }

    public Page<ProductEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.productPagSortRepository.findAll(pageRequest);
    }

    public Page<ProductEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        System.out.println(this.productRepository.countByNoWoolTrue());

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);

        return this.productPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public ProductEntity getByName(String name) {
        return this.productRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("La producto no existe"));
    }

    public List<ProductEntity> getWith(String available) {
        return this.productRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(available);
    }

    public List<ProductEntity> getWithout(String available) {
        return this.productRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(available);
    }

    public List<ProductEntity> getCheapest(double price) {
        return this.productRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public ProductEntity get(int idProduct) {
        return this.productRepository.findById(idProduct).orElse(null);
    }

    public ProductEntity save(ProductEntity product) {
        return this.productRepository.save(product);
    }

    public void delete(int idProduct) {
        this.productRepository.deleteById(idProduct);
    }

    @Transactional(noRollbackFor = EmailApiException.class)
    public void updatePrice(UpdateProductPriceDto dto) {
        this.productRepository.updatePrice(dto);
        this.sendEmail();
    }

    private void sendEmail() {
        throw new EmailApiException();
    }

    public boolean exists(int idProduct) {
        return this.productRepository.existsById(idProduct);
    }
}
