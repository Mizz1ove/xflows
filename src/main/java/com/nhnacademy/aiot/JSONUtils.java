package com.nhnacademy.aiot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public JSONUtils(){
    }

    public static ObjectMapper getMapper(){
        return objectMapper;
    }

    public static JsonNode parseJson(String payload){
        try{
            return objectMapper.readTree(payload);
        }catch(Exception e){
            return null;
        }
    }

    public static String objectToJsonString(Object obj){
        try{
            return objectMapper.writeValueAsString(obj);
        }catch(Exception e){
            return null;
        }
    }
}
