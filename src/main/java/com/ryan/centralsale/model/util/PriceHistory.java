package com.ryan.centralsale.model.util;

import com.ryan.centralsale.model.Product;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "price_history")
@Data
public class PriceHistory {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private double price;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    private boolean isActive; // Indicates if this price is the current active price
}
