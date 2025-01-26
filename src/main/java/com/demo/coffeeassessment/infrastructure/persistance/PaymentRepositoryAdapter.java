package com.demo.coffeeassessment.infrastructure.persistance;

import com.demo.coffeeassessment.core.domain.Payment;
import com.demo.coffeeassessment.core.port.outbound.PaymentRepository;
import com.demo.coffeeassessment.infrastructure.persistance.entity.PaymentEntity;
import com.demo.coffeeassessment.infrastructure.persistance.jpa.PaymentJpaRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryAdapter implements PaymentRepository {

  private final PaymentJpaRepository paymentJpaRepository;
  private final ModelMapper modelMapper;

  @Autowired
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
