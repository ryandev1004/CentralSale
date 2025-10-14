package com.ryan.centralsale.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@Table(name = "tracker_table")
public class UserProduct {

    @Id
    @UuidGenerator
    private UUID trackerId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "price_check", nullable = false)
    private double priceCheck;

    private boolean isActive; // Indicates if the tracking is active


}
