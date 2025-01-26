package com.demo.coffeeassessment.core.port.outbound;

import com.demo.coffeeassessment.core.domain.Product;
import java.util.List;

public interface ProductRepository {
  List<Product> findAll();

  void saveAll(List<Product> products);
}
