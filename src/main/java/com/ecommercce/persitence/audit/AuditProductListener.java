package com.ecommercce.persitence.audit;

import com.ecommercce.persitence.entity.ProductEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditProductListener {
    private ProductEntity currentValue;

    @PostLoad
    public void postLoad(ProductEntity entity) {
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(ProductEntity entity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NEW VALUE: " + entity.toString());
    }

    @PreRemove
    public void onPreDelete(ProductEntity entity) {
        System.out.println(entity.toString());
    }
}
