package com.demo.coffeepayments.infrastructure.persistance;

import com.demo.coffeepayments.core.domain.Payment;
import com.demo.coffeepayments.core.port.PaymentRepository;
import com.demo.coffeepayments.infrastructure.persistance.entity.PaymentEntity;
import com.demo.coffeepayments.infrastructure.persistance.jpa.PaymentJpaRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryAdapter implements PaymentRepository {

  private final PaymentJpaRepository paymentJpaRepository;
  private final ModelMapper modelMapper;

  public PaymentRepositoryAdapter(PaymentJpaRepository paymentJpaRepository) {
    this.paymentJpaRepository = paymentJpaRepository;
    this.modelMapper = new ModelMapper().registerModule(new RecordModule());
  }

  @Override
  public List<Payment> findAll() {
    return paymentJpaRepository.findAll().stream().map(PaymentEntity::toDomain).toList();
  }

  @Override
  public void saveAll(List<Payment> payments) {
    paymentJpaRepository.saveAll(
        payments.stream().map(payment -> modelMapper.map(payment, PaymentEntity.class)).toList());
  }
}
