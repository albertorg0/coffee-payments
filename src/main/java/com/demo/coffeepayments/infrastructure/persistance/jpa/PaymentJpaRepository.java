package com.demo.coffeepayments.infrastructure.persistance.jpa;

import com.demo.coffeepayments.infrastructure.persistance.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {}
