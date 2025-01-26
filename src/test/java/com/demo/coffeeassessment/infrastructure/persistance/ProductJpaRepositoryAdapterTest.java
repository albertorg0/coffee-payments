package com.demo.coffeeassessment.infrastructure.persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import com.demo.coffeeassessment.core.domain.Product;
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
  void whenFindPriceByProductAndSize_ShouldReturnDouble() {
    // Given
    when(productJpaRepository.findPriceByProductAndSize("Coffee", "small")).thenReturn(5.0);

    // When
    Double result = productRepositoryAdapter.findPriceByProductAndSize("Coffee", "small");

    // Then
    verify(productJpaRepository, times(1)).findPriceByProductAndSize("Coffee", "small");

    assertEquals(5.0, result);
  }

  @Test
  void whenSaveAll_ShouldCallRepositorySave() {
    // Given
    Product product = new Product("Coffee", Map.of("small", 5.0));

    // When
    productRepositoryAdapter.saveAll(List.of(product));

    // Then
    verify(productJpaRepository, times(1)).saveAll(anyList());
  }
}
