package com.demo.coffeeassessment.infrastructure.persistance;

import com.demo.coffeeassessment.core.domain.Order;
import com.demo.coffeeassessment.core.port.outbound.OrderRepository;
import com.demo.coffeeassessment.infrastructure.persistance.entity.OrderEntity;
import com.demo.coffeeassessment.infrastructure.persistance.jpa.OrderJpaRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryAdapter implements OrderRepository {

  private final OrderJpaRepository orderJpaRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository) {
    this.orderJpaRepository = orderJpaRepository;
    this.modelMapper = new ModelMapper().registerModule(new RecordModule());
  }

  @Override
  public List<Order> findAll() {
    return orderJpaRepository.findAll().stream().map(OrderEntity::toDomain).toList();
  }

  @Override
  public void saveAll(List<Order> orders) {
    orderJpaRepository.saveAll(
        orders.stream().map(order -> modelMapper.map(order, OrderEntity.class)).toList());
  }
}
