package com.demo.coffeeassessment.infrastructure.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.demo.coffeeassessment.core.PaymentService;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

  private MockMvc mockMvc;

  @Mock private PaymentService paymentService;

  @InjectMocks private PaymentController paymentController;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
  }

  @Test
  public void whenGetAmountPaidPerUser_ShouldReturnOkResponse() throws Exception {
    Map<String, Double> amountPaidPerUser = new HashMap<>();
    amountPaidPerUser.put("user1", 70.0);
    amountPaidPerUser.put("user2", 30.0);

    when(paymentService.getAmountPaidPerUser()).thenReturn(amountPaidPerUser);

    mockMvc
        .perform(get("/payments/amount-paid"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$[0].user").value("user1"))
        .andExpect(jsonPath("$[0].amountPaid").value(70.0))
        .andExpect(jsonPath("$[1].user").value("user2"))
        .andExpect(jsonPath("$[1].amountPaid").value(30.0));
  }

  @Test
  public void whenGetAmountOwedPerUser_ShouldReturnOkResponse() throws Exception {
    Map<String, Double> amountOwedPerUser = new HashMap<>();
    amountOwedPerUser.put("user1", 0.0);
    amountOwedPerUser.put("user2", -10.0);

    when(paymentService.getAmountOwedPerUser()).thenReturn(amountOwedPerUser);

    mockMvc
        .perform(get("/payments/amount-owed"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$[0].user").value("user1"))
        .andExpect(jsonPath("$[0].amountOwed").value(0.0))
        .andExpect(jsonPath("$[1].user").value("user2"))
        .andExpect(jsonPath("$[1].amountOwed").value(-10.0));
  }
}
