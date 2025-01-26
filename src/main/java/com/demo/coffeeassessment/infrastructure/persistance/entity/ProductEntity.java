package com.demo.coffeeassessment.infrastructure.persistance.entity;

import com.demo.coffeeassessment.core.domain.Product;
import com.demo.coffeeassessment.infrastructure.config.MapToStringConverter;
import jakarta.persistence.*;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ElementCollection
//  @Convert(converter = MapToStringConverter.class)
  Map<String, Double> prices;

  public Product toDomain() {
    return new Product(name, prices);
  }
}
