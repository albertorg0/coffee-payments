package com.demo.coffeeassessment.core.port.outbound;

import com.demo.coffeeassessment.core.domain.Order;
import java.util.List;

public interface OrderRepository {
  List<Order> findAll();

  void saveAll(List<Order> orders);
}
