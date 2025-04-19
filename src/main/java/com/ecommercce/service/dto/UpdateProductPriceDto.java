package com.ecommercce.service.dto;

import lombok.Data;

@Data
public class UpdateProductPriceDto {
    private int productId;
    private double newPrice;
}
