package com.nhnacademy.aiot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public JSONUtils() {
    }

    public static ObjectMapper getMapper() {
        return objectMapper;
    }

    public static JsonNode parseJson(String payload) {
        try {
            return objectMapper.readTree(payload);
        } catch (Exception e) {
            return null;
        }
    }
    public String convertToString(JsonNode jsonNode) {
        return jsonNode.asText();
    }

    public int convertToInt(JsonNode jsonNode) {
        return jsonNode.asInt();
    }

    public double convertToDouble(JsonNode jsonNode) {
        return jsonNode.asDouble();
    }


}
