package com.demo.coffeeassessment.core.port;

import com.demo.coffeeassessment.core.domain.Product;
import java.util.List;

public interface ProductRepository {
  Double findPriceByProductAndSize(String product, String size);

  void saveAll(List<Product> products);
}
