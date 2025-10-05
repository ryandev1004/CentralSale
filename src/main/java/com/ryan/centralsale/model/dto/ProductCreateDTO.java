package com.ryan.centralsale.model.dto;


import lombok.Data;



@Data
public class ProductCreateDTO {

    private String asin; // Amazon Standard Identification Number
    private String productUrl;
    private String title;
    private String imageUrl;
    private double currentPrice;
    private boolean status; // true if available, false if unavailable

}
