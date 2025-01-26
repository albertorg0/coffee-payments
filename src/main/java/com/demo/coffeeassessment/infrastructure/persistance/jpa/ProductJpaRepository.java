package com.demo.coffeeassessment.infrastructure.persistance.jpa;

import com.demo.coffeeassessment.infrastructure.persistance.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

  @Query("SELECT p.prices[:size] FROM ProductEntity p WHERE p.name = :product")
  Double findPriceByProductAndSize(@Param("product") String product, @Param("size") String size);
}
