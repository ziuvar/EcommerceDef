package com.ecommercce.service.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private String title;
    private String content;
    private int rating;
    private String username;
}
