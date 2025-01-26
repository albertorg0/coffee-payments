package com.demo.coffeepayments.core.port;

import com.demo.coffeepayments.core.domain.Payment;
import java.util.List;

public interface PaymentRepository {
  List<Payment> findAll();

  void saveAll(List<Payment> payments);
}
