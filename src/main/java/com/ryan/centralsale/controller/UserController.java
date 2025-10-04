package com.ryan.centralsale.controller;

import com.ryan.centralsale.model.dto.UserCreateDTO;
import com.ryan.centralsale.model.dto.UserDTO;
import com.ryan.centralsale.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }
}
