package com.ryan.centralsale.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserProductDTO {
    private UUID trackerId;
    private double priceCheck;
    private ProductDTO product;
}
