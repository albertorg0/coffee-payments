package com.demo.coffeeassessment.infrastructure.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class MapToStringConverter implements AttributeConverter<Map<String, Double>, String> {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(Map<String, Double> attribute) {
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Error converting map to JSON string", e);
    }
  }

  @Override
  public Map<String, Double> convertToEntityAttribute(String dbData) {
    try {
      return objectMapper.readValue(dbData, HashMap.class);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error converting JSON string to map", e);
    }
  }
}
