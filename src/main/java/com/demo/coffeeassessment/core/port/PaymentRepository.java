package com.demo.coffeeassessment.core.port;

import com.demo.coffeeassessment.core.domain.Payment;
import java.util.List;

public interface PaymentRepository {
  List<Payment> findAll();

  void saveAll(List<Payment> payments);
}
