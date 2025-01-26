package com.demo.coffeeassessment.infrastructure.rest;

import com.demo.coffeeassessment.core.PaymentService;
import com.demo.coffeeassessment.infrastructure.rest.rps.AmountOwedResponse;
import com.demo.coffeeassessment.infrastructure.rest.rps.AmountPaidResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @GetMapping("/amount-paid")
  public List<AmountPaidResponse> getAmountPaidPerUser() {
    Map<String, Double> amountPaidPerUser = paymentService.getAmountPaidPerUser();
    return amountPaidPerUser.entrySet().stream()
        .map(entry -> new AmountPaidResponse(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }

  @GetMapping("/amount-owed")
  public List<AmountOwedResponse> getAmountOwedPerUser() {
    Map<String, Double> amountOwedPerUser = paymentService.getAmountOwedPerUser();
    return amountOwedPerUser.entrySet().stream()
        .map(entry -> new AmountOwedResponse(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }
}
