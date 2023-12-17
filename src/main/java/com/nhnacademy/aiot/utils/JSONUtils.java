package com.nhnacademy.aiot.utils;

import java.io.File;
import java.io.Reader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JSONUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private JSONUtils() {
    }

    public static boolean isJson(String jsonString) {
        try {
            objectMapper.readTree(jsonString);
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    public static ObjectMapper getMapper() {
        return objectMapper;
    }

    public static ObjectNode parseJson(String jsonString) {
        try {
            return (ObjectNode) objectMapper.readTree(jsonString);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    public static ObjectNode parseJson(File jsonFile) {
        try {
            return (ObjectNode) objectMapper.readTree(jsonFile);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    public static ObjectNode parseJson(Reader reader) {
        try {
            return (ObjectNode) objectMapper.readTree(reader);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }


}