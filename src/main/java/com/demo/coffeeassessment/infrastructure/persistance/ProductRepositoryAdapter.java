package com.demo.coffeeassessment.infrastructure.persistance;

import com.demo.coffeeassessment.core.domain.Product;
import com.demo.coffeeassessment.core.port.outbound.ProductRepository;
import com.demo.coffeeassessment.infrastructure.persistance.entity.ProductEntity;
import com.demo.coffeeassessment.infrastructure.persistance.jpa.ProductJpaRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

  private final ProductJpaRepository productJpaRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository) {
    this.productJpaRepository = productJpaRepository;
    this.modelMapper = new ModelMapper().registerModule(new RecordModule());
  }

  @Override
  public List<Product> findAll() {
    return productJpaRepository.findAll().stream().map(ProductEntity::toDomain).toList();
  }

  @Override
  public void saveAll(List<Product> products) {
    productJpaRepository.saveAll(
        products.stream().map(product -> modelMapper.map(product, ProductEntity.class)).toList());
  }
}
