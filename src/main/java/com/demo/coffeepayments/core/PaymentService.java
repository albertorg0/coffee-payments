package com.demo.coffeepayments.core;

import java.util.Map;

public interface PaymentService {
    Map<String, Double> getAmountPaidPerUser();
    Map<String, Double> getAmountOwedPerUser();
}
