package com.ecommercce.web.controller;

import com.ecommercce.persitence.entity.ProductEntity;
import com.ecommercce.service.ProductService;
import com.ecommercce.service.dto.UpdateProductPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "8") int elements) {
        return ResponseEntity.ok(this.productService.getAll(page, elements));
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductEntity> get(@PathVariable int idProduct) {
        return ResponseEntity.ok(this.productService.get(idProduct));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<ProductEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "8") int elements,
                                                            @RequestParam(defaultValue = "price") String sortBy,
                                                            @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(this.productService.getAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(this.productService.getByName(name));
    }

    @GetMapping("/with/{available}")
    public ResponseEntity<List<ProductEntity>> getWith(@PathVariable String available) {
        return ResponseEntity.ok(this.productService.getWith(available));
    }

    @GetMapping("/without/{available}")
    public ResponseEntity<List<ProductEntity>> getWithout(@PathVariable String available) {
        return ResponseEntity.ok(this.productService.getWithout(available));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<ProductEntity>> getCheapestProducts(@PathVariable double price) {
        return ResponseEntity.ok(this.productService.getCheapest(price));
    }

    @PostMapping
    public ResponseEntity<ProductEntity> add(@RequestBody ProductEntity product) {
        if (product.getIdProduct() == null || !this.productService.exists(product.getIdProduct())) {
            return ResponseEntity.ok(this.productService.save(product));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<ProductEntity> update(@RequestBody ProductEntity product) {
        if (product.getIdProduct() != null && this.productService.exists(product.getIdProduct())) {
            return ResponseEntity.ok(this.productService.save(product));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdateProductPriceDto dto) {
        if (this.productService.exists(dto.getProductId())) {
            this.productService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> delete(@PathVariable int idProduct) {
        if (this.productService.exists(idProduct)) {
            this.productService.delete(idProduct);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
