package com.ryan.centralsale.model.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserCreateDTO {
    @Email(message = "Invalid email format")
    private String email;
    private String password;
}
