package com.ryan.centralsale.controller;

import com.ryan.centralsale.model.dto.UserProductCreateDTO;
import com.ryan.centralsale.model.dto.UserProductDTO;
import com.ryan.centralsale.service.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/products")
public class UserProductController {

    private final UserProductService userProductService;

    @PostMapping
    public ResponseEntity<UserProductDTO> createUserProduct(@RequestBody UserProductCreateDTO request){
        return ResponseEntity.ok().body(userProductService.createUserProduct(request.getUserId(), request.getUrl()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserProductDTO>> getAllUserProducts(@PathVariable UUID userId){
        return ResponseEntity.ok().body(userProductService.getAllUserProducts(userId));
    }
}
