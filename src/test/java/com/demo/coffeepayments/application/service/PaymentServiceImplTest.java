package com.demo.coffeepayments.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.demo.coffeepayments.core.domain.Order;
import com.demo.coffeepayments.core.domain.Payment;
import com.demo.coffeepayments.infrastructure.persistance.OrderRepositoryAdapter;
import com.demo.coffeepayments.infrastructure.persistance.PaymentRepositoryAdapter;
import com.demo.coffeepayments.infrastructure.persistance.ProductRepositoryAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

  @Mock private PaymentRepositoryAdapter paymentRepositoryAdapter;
  @Mock private OrderRepositoryAdapter orderRepositoryAdapter;
  @Mock private ProductRepositoryAdapter productRepositoryAdapter;

  @InjectMocks private PaymentServiceImpl paymentService;

  @Test
  void whenGetAmountPaidPerUser_ShouldReturnMap() {
    // Given
    List<Payment> payments =
        Arrays.asList(
            new Payment("user1", 50.0), new Payment("user2", 30.0), new Payment("user1", 20.0));

    when(paymentRepositoryAdapter.findAll()).thenReturn(payments);

    // When
    Map<String, Double> result = paymentService.getAmountPaidPerUser();

    // Then
    assertEquals(70.0, result.get("user1"));
    assertEquals(30.0, result.get("user2"));
  }

  @Test
  void whenGetAmountOwedPerUser_ShouldReturnMap() {
    // Given
    List<Order> orders =
        Arrays.asList(
            new Order("user1", "coffee", "large"),
            new Order("user2", "tea", "medium"),
            new Order("user1", "coffee", "small"));

    List<Payment> payments = Arrays.asList(new Payment("user1", 50.0), new Payment("user2", 30.0));

    when(orderRepositoryAdapter.findAll()).thenReturn(orders);
    when(paymentRepositoryAdapter.findAll()).thenReturn(payments);
    when(productRepositoryAdapter.findPriceByProductAndSize("coffee", "large")).thenReturn(40.0);
    when(productRepositoryAdapter.findPriceByProductAndSize("tea", "medium")).thenReturn(20.0);
    when(productRepositoryAdapter.findPriceByProductAndSize("coffee", "small")).thenReturn(10.0);

    // When
    Map<String, Double> result = paymentService.getAmountOwedPerUser();

    // Then
    assertEquals(0.0, result.get("user1"));
    assertEquals(-10.0, result.get("user2"));
  }
}
