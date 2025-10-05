package com.ryan.centralsale.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserProductCreateDTO {
    private UUID userId;
    private String url;
}
