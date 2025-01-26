package com.demo.coffeeassessment.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record Product (@JsonProperty("drink_name") String name, Map<String, Double> prices) {}
