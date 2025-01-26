package com.demo.coffeeassessment.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Order(String user, @JsonProperty("drink") String product, String size) {}
