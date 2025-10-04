package com.ryan.centralsale.repository.util;

import com.ryan.centralsale.model.util.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, UUID> {
}
