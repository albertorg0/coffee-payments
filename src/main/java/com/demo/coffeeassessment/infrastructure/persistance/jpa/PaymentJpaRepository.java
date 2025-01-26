package com.demo.coffeeassessment.infrastructure.persistance.jpa;

import com.demo.coffeeassessment.infrastructure.persistance.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {}
