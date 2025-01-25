package com.demo.coffeeassessment.core.domain;

import java.util.Map;

public record Product (String name, Map<String, Double> prices) {}
