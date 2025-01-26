package com.demo.coffeeassessment.infrastructure.persistance.jpa;

import com.demo.coffeeassessment.infrastructure.persistance.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {}
