package com.ryan.centralsale.model.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String asin; // Amazon Standard Identification Number
    private String productUrl;
    private String title;
    private String imageUrl;
    private double currentPrice;
    private double percentChange;
}
