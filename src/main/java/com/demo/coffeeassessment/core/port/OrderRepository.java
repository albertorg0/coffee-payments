package com.demo.coffeeassessment.core.port;

import com.demo.coffeeassessment.core.domain.Order;
import java.util.List;

public interface OrderRepository {
  List<Order> findAll();

  void saveAll(List<Order> orders);
}
