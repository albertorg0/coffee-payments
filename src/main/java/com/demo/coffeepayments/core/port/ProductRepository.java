package com.demo.coffeepayments.core.port;

import com.demo.coffeepayments.core.domain.Product;
import java.util.List;

public interface ProductRepository {
  Double findPriceByProductAndSize(String product, String size);

  void saveAll(List<Product> products);
}
