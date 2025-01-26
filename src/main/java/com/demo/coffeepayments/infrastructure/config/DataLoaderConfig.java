package com.demo.coffeepayments.infrastructure.config;

import com.demo.coffeepayments.core.domain.Order;
import com.demo.coffeepayments.core.domain.Payment;
import com.demo.coffeepayments.core.domain.Product;
import com.demo.coffeepayments.core.port.OrderRepository;
import com.demo.coffeepayments.core.port.PaymentRepository;
import com.demo.coffeepayments.core.port.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoaderConfig {

  private final OrderRepository orderRepository;
  private final PaymentRepository paymentRepository;
  private final ProductRepository productRepository;

  public DataLoaderConfig(
      OrderRepository orderRepository,
      PaymentRepository paymentRepository,
      ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.paymentRepository = paymentRepository;
    this.productRepository = productRepository;
  }

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
