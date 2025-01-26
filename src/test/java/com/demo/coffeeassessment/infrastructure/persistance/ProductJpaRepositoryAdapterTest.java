package com.demo.coffeeassessment.infrastructure.persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import com.demo.coffeeassessment.core.domain.Product;
import com.demo.coffeeassessment.infrastructure.persistance.entity.ProductEntity;
import com.demo.coffeeassessment.infrastructure.persistance.jpa.ProductJpaRepository;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductJpaRepositoryAdapterTest {

  private ProductJpaRepository productJpaRepository;
  private ProductRepositoryAdapter productRepositoryAdapter;

  @BeforeEach
  void setUp() {
    productJpaRepository = mock(ProductJpaRepository.class);
    productRepositoryAdapter = new ProductRepositoryAdapter(productJpaRepository);
  }

  @Test
  void testFindAll() {
    ProductEntity productEntity = new ProductEntity();
    productEntity.setId(1L);
    productEntity.setName("Coffee");
    productEntity.setPrices(Map.of("small", 5.0));
    when(productJpaRepository.findAll()).thenReturn(List.of(productEntity));

    List<Product> products = productRepositoryAdapter.findAll();

    assertEquals(1, products.size());
    assertEquals("Coffee", products.get(0).name());
    assertEquals(5.0, products.get(0).prices().get("small"));
  }

  @Test
  void testSaveAll() {
    Product product = new Product("Coffee", Map.of("small", 5.0));

    productRepositoryAdapter.saveAll(List.of(product));

    verify(productJpaRepository, times(1)).saveAll(anyList());
  }
}
