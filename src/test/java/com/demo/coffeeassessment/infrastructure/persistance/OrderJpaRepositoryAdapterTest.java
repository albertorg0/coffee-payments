package com.demo.coffeeassessment.infrastructure.persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import com.demo.coffeeassessment.core.domain.Order;
import com.demo.coffeeassessment.infrastructure.persistance.entity.OrderEntity;
import com.demo.coffeeassessment.infrastructure.persistance.jpa.OrderJpaRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderJpaRepositoryAdapterTest {
  private OrderJpaRepository orderJpaRepository;
  private OrderRepositoryAdapter orderRepositoryAdapter;

  @BeforeEach
  void setUp() {
    orderJpaRepository = mock(OrderJpaRepository.class);
    orderRepositoryAdapter = new OrderRepositoryAdapter(orderJpaRepository);
  }

  @Test
  void testFindAll() {
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setId(1L);
    orderEntity.setUser("John");
    orderEntity.setProduct("Coffee");
    orderEntity.setSize("Large");
    when(orderJpaRepository.findAll()).thenReturn(List.of(orderEntity));

    List<Order> orders = orderRepositoryAdapter.findAll();

    assertEquals(1, orders.size());
    assertEquals("John", orders.get(0).user());
    assertEquals("Coffee", orders.get(0).product());
    assertEquals("Large", orders.get(0).size());
  }

  @Test
  void testSaveAll() {
    Order order = new Order("John", "Coffee", "Large");

    orderRepositoryAdapter.saveAll(List.of(order));

    verify(orderJpaRepository, times(1)).saveAll(anyList());
  }
}
