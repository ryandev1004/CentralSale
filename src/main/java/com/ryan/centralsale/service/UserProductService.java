package com.ryan.centralsale.service;

import com.ryan.centralsale.mapper.UserProductMapper;
import com.ryan.centralsale.model.Product;
import com.ryan.centralsale.model.User;
import com.ryan.centralsale.model.UserProduct;
import com.ryan.centralsale.model.dto.UserProductDTO;
import com.ryan.centralsale.repository.UserProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProductService {

    private final UserService userService;
    private final UserProductRepository userProductRepository;
    private final UserProductMapper userProductMapper;
    private final ProductService productService;

    public UserProductDTO createUserProduct(UUID userId, String url) {
        User user = userService.findEntityById(userId);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        String asin = productService.extractAsinFromUrl(url)
                .orElseThrow(() -> new RuntimeException("Invalid Amazon URL: " + url));
        Product product = productService.fetchProduct(asin);
        if (product == null) {
            throw new RuntimeException("Failed to fetch product with ASIN: " + asin);
        }
        UserProduct userProduct = userProductMapper.toEntity(user, product);
        return userProductMapper.toDTO(userProductRepository.save(userProduct));
    }
}
