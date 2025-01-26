package com.demo.coffeepayments.infrastructure.persistance.jpa;

import com.demo.coffeepayments.infrastructure.persistance.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {}
