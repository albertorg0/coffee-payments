package com.demo.coffeepayments.infrastructure.persistance.entity;

import com.demo.coffeepayments.core.domain.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "`user`", columnDefinition = "varchar(255)")
  private String user;

  private String product;
  private String size;

  public Order toDomain() {
    return new Order(user, product, size);
  }
}
