package com.ryan.centralsale.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID userId;
    private String email;
}
