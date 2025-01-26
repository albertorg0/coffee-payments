package com.demo.coffeepayments.core.port;

import com.demo.coffeepayments.core.domain.Order;
import java.util.List;

public interface OrderRepository {
  List<Order> findAll();

  void saveAll(List<Order> orders);
}
