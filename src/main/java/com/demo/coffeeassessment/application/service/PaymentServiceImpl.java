package com.demo.coffeeassessment.application.service;

import com.demo.coffeeassessment.core.PaymentService;
import com.demo.coffeeassessment.core.domain.Order;
import com.demo.coffeeassessment.core.domain.Payment;
import com.demo.coffeeassessment.infrastructure.persistance.OrderRepositoryAdapter;
import com.demo.coffeeassessment.infrastructure.persistance.PaymentRepositoryAdapter;
import com.demo.coffeeassessment.infrastructure.persistance.ProductRepositoryAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepositoryAdapter paymentRepositoryAdapter;
  private final OrderRepositoryAdapter orderRepositoryAdapter;
  private final ProductRepositoryAdapter productRepositoryAdapter;

  public PaymentServiceImpl(
      PaymentRepositoryAdapter paymentRepositoryAdapter,
      OrderRepositoryAdapter orderRepositoryAdapter,
      ProductRepositoryAdapter productRepositoryAdapter) {
    this.paymentRepositoryAdapter = paymentRepositoryAdapter;
    this.orderRepositoryAdapter = orderRepositoryAdapter;
    this.productRepositoryAdapter = productRepositoryAdapter;
  }

  @Override
  public Map<String, Double> getAmountPaidPerUser() {
    List<Payment> payments = paymentRepositoryAdapter.findAll();
    Map<String, Double> amountPaidPerUser = new HashMap<>();
    for (Payment payment : payments) {
      amountPaidPerUser.merge(payment.user(), payment.amount(), Double::sum);
    }
    return amountPaidPerUser;
  }

  @Override
  public Map<String, Double> getAmountOwedPerUser() {
    List<Order> orders = orderRepositoryAdapter.findAll();
    List<Payment> payments = paymentRepositoryAdapter.findAll();
    Map<String, Double> totalOrderAmountPerUser = new HashMap<>();
    Map<String, Double> amountPaidPerUser = new HashMap<>();
    Map<String, Double> amountOwedPerUser = new HashMap<>();

    // Calculate total order amount per user
    for (Order order : orders) {
      double price =
          productRepositoryAdapter.findPriceByProductAndSize(order.drink(), order.size());
      totalOrderAmountPerUser.merge(order.user(), price, Double::sum);
    }

    // Calculate amount paid per user
    for (Payment payment : payments) {
      amountPaidPerUser.merge(payment.user(), payment.amount(), Double::sum);
    }

    // Calculate amount owed per user
    for (Map.Entry<String, Double> entry : totalOrderAmountPerUser.entrySet()) {
      String user = entry.getKey();
      double totalOrderAmount = entry.getValue();
      double amountPaid = amountPaidPerUser.getOrDefault(user, 0.0);
      double amountOwed = totalOrderAmount - amountPaid;
      amountOwedPerUser.put(user, amountOwed);
    }

    return amountOwedPerUser;
  }
}
