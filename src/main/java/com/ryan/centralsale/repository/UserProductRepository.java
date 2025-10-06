package com.ryan.centralsale.repository;

import com.ryan.centralsale.model.Product;
import com.ryan.centralsale.model.User;
import com.ryan.centralsale.model.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserProductRepository extends JpaRepository<UserProduct, UUID> {
    List<UserProduct> findByProductAndIsActiveTrue(Product product);

    List<UserProduct> findByUser(User user);
}
