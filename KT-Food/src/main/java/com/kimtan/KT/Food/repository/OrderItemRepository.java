package com.kimtan.KT.Food.repository;

import com.kimtan.KT.Food.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
