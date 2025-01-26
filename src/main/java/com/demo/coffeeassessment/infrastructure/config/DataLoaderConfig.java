package com.demo.coffeeassessment.infrastructure.config;

import com.demo.coffeeassessment.core.domain.Order;
import com.demo.coffeeassessment.core.domain.Payment;
import com.demo.coffeeassessment.core.domain.Product;
import com.demo.coffeeassessment.core.port.outbound.OrderRepository;
import com.demo.coffeeassessment.core.port.outbound.PaymentRepository;
import com.demo.coffeeassessment.core.port.outbound.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class DataLoaderConfig {

  @Autowired private OrderRepository orderRepository;
  @Autowired private PaymentRepository paymentRepository;
  @Autowired private ProductRepository productRepository;

  @PostConstruct
  public void init() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    List<Product> products =
        mapper.readValue(
            Paths.get("src/main/resources/products.json").toFile(), new TypeReference<>() {});
    productRepository.saveAll(products);

    List<Order> orders =
        mapper.readValue(
            Paths.get("src/main/resources/orders.json").toFile(), new TypeReference<>() {});
    orderRepository.saveAll(orders);

    List<Payment> payments =
        mapper.readValue(
            Paths.get("src/main/resources/payments.json").toFile(), new TypeReference<>() {});
    paymentRepository.saveAll(payments);
  }
}
