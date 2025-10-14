package com.ryan.centralsale.repository;

import com.ryan.centralsale.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByAsin(String asin);

    @Query("SELECT p FROM Product p WHERE p.lastChecked IS NULL OR p.lastChecked < :cutoffTime")
    List<Product> findAllUncheckedProducts(LocalDateTime cutoffTime);

    @Query("SELECT p FROM Product p LEFT JOIN UserProduct up ON p.productId = up.product.productId WHERE up.trackerId IS NULL")
    List<Product> findUnusedProducts();
}
