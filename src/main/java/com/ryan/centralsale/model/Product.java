package com.ryan.centralsale.model;

import com.ryan.centralsale.model.util.PriceHistory;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Not the product for our Users to be tracking, but products will be saved and
 * reused in the future if other users try to track the same product.
 */
@Entity
@Data
@Table(name = "products_table")
public class Product {

    @Id
    @UuidGenerator
    private UUID productId;

    @Column(unique = true, nullable = false)
    private String asin; // Amazon Standard Identification Number

    private String productUrl;
    private String title;
    private String imageUrl;

    @Column(name = "current_price")
    private double currentPrice;

    @Column(name = "last_checked")
    private LocalDateTime lastChecked;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // Timestamp to show how the product has been tracked in our system

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PriceHistory> priceHistory = new ArrayList<>();


}
