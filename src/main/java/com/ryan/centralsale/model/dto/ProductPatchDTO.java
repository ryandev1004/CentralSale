package com.ryan.centralsale.model.dto;

import lombok.Data;

@Data
public class ProductPatchDTO {
    private String title;
    private String imageUrl;
    private String productUrl;
    private Double currentPrice;
    private Double percentChange;
}
