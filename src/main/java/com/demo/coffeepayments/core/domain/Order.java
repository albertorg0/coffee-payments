package com.demo.coffeepayments.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Order(String user, @JsonProperty("drink") String product, String size) {}
