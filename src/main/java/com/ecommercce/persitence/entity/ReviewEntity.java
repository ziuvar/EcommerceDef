package com.ecommercce.persitence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor

public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review", nullable = false)
    private Long idReview;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer")
    private CustomerEntity customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_product", referencedColumnName = "id_product")
    private ProductEntity product;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 255)
    private String comment;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean approved;

    @ManyToOne
    @JoinColumn(name = "moderated_by", referencedColumnName = "username")
    private UserEntity moderator;

    @Override
    public String toString() {
        return "ReviewEntity{" +
                "idReview=" + idReview +
                ", customer=" + (customer != null ? customer.getIdCustomer() : null) +
                ", product=" + (product != null ? product.getIdProduct() : null) +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", approved=" + approved +
                ", moderator=" + (moderator != null ? moderator.getUsername() : null) +
                '}';
    }
}