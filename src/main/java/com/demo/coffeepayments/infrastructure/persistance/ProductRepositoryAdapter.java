package com.demo.coffeepayments.infrastructure.persistance;

import com.demo.coffeepayments.core.domain.Product;
import com.demo.coffeepayments.core.port.ProductRepository;
import com.demo.coffeepayments.infrastructure.persistance.entity.ProductEntity;
import com.demo.coffeepayments.infrastructure.persistance.jpa.ProductJpaRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

  private final ProductJpaRepository productJpaRepository;
  private final ModelMapper modelMapper;

  public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository) {
    this.productJpaRepository = productJpaRepository;
    this.modelMapper = new ModelMapper().registerModule(new RecordModule());
  }

  @Override
  public Double findPriceByProductAndSize(String product, String size) {
    return productJpaRepository.findPriceByProductAndSize(product, size);
  }

  @Override
  public void saveAll(List<Product> products) {
    productJpaRepository.saveAll(
        products.stream().map(product -> modelMapper.map(product, ProductEntity.class)).toList());
  }
}
