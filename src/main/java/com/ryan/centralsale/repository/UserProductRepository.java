package com.ryan.centralsale.repository;

import com.ryan.centralsale.model.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProductRepository extends JpaRepository<UserProduct, UUID> {
}
