package com.demo.coffeepayments.infrastructure.persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import com.demo.coffeepayments.core.domain.Payment;
import com.demo.coffeepayments.infrastructure.persistance.entity.PaymentEntity;
import com.demo.coffeepayments.infrastructure.persistance.jpa.PaymentJpaRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentJpaRepositoryAdapterTest {
  private PaymentJpaRepository paymentJpaRepository;
  private PaymentRepositoryAdapter paymentRepositoryAdapter;

  @BeforeEach
  void setUp() {
    paymentJpaRepository = mock(PaymentJpaRepository.class);
    paymentRepositoryAdapter = new PaymentRepositoryAdapter(paymentJpaRepository);
  }

  @Test
  void whenFindAll_ShouldReturnPaymentList() {
    // Given
    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.setId(1L);
    paymentEntity.setUser("John");
    paymentEntity.setAmount(100);
    when(paymentJpaRepository.findAll()).thenReturn(List.of(paymentEntity));

    // When
    List<Payment> payments = paymentRepositoryAdapter.findAll();

    // Then
    verify(paymentJpaRepository, times(1)).findAll();

    assertEquals(1, payments.size());
    assertEquals("John", payments.get(0).user());
    assertEquals(100, payments.get(0).amount());
  }

  @Test
  void whenSaveAll_ShouldCallRepositorySave() {
    // Given
    Payment payment = new Payment("John", 100);

    // When
    paymentRepositoryAdapter.saveAll(List.of(payment));

    // Then
    verify(paymentJpaRepository, times(1)).saveAll(anyList());
  }
}
