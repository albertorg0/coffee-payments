package com.demo.coffeepayments.infrastructure.persistance.entity;

import com.demo.coffeepayments.core.domain.Payment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PaymentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "`user`", columnDefinition = "varchar(255)")
  private String user;

  private int amount;

  public Payment toDomain() {
    return new Payment(user, amount);
  }
}
