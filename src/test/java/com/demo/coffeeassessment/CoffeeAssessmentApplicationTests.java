package com.demo.coffeeassessment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CoffeeAssessmentApplicationTests {

  @Autowired private MockMvc mockMvc;

  @Test
  void contextLoads() {}

  @Test
  void testGetAmountPaidPerUser() throws Exception {
    mockMvc
        .perform(get("/payments/amount-paid"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$").isArray());
  }

  @Test
  void testGetAmountOwedPerUser() throws Exception {
    mockMvc
        .perform(get("/payments/amount-owed"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$").isArray());
  }
}
