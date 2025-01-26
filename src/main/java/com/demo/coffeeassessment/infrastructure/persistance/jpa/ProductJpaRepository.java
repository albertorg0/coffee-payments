package com.demo.coffeeassessment.infrastructure.persistance.jpa;

import com.demo.coffeeassessment.infrastructure.persistance.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {}
