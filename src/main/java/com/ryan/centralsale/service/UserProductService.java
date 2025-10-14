package com.ryan.centralsale.service;

import com.ryan.centralsale.mapper.UserProductMapper;
import com.ryan.centralsale.model.Product;
import com.ryan.centralsale.model.User;
import com.ryan.centralsale.model.UserProduct;
import com.ryan.centralsale.model.dto.UserProductDTO;
import com.ryan.centralsale.repository.UserProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProductService {

    private final UserService userService;
    private final UserProductRepository userProductRepository;
    private final UserProductMapper userProductMapper;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private ProductService productService;

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
        userProduct.setActive(true);
        return userProductMapper.toDTO(userProductRepository.save(userProduct));
    }

    public List<UserProductDTO> getAllUserProducts(UUID userId){
        User user = userService.findEntityById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }
        List<UserProduct> userProducts = userProductRepository.findByUser(user);
        return userProducts.stream().map(userProductMapper::toDTO).toList();
    }

    public void removeUserProduct(UUID trackerId) {
        UserProduct userProduct = userProductRepository.findById(trackerId)
                .orElseThrow(() -> new EntityNotFoundException("UserProduct not found with ID: " + trackerId));
        userProductRepository.delete(userProduct);
    }

    public List<UserProduct> getActiveUserProductsByProduct(Product product) {
        return userProductRepository.findByProductAndIsActiveTrue(product);
    }
}
