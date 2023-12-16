package com.nhnacademy.aiot.node;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.SensorData;
import com.nhnacademy.aiot.Wire;
import com.nhnacademy.aiot.db.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModbusMapperTest {

    @Test
    @BeforeEach
    @DisplayName("DB에 테스트용 데이터 추가")
    void setup(){
        SensorData testSensorData = SensorData.builder()
            .deviceId("24e124128c067999-temperature")
            .site("nhnacademy")
            .branch("gyeongnam")
            .place("class_a")
            .register("input")
            .address(101)
            .ratio(0.1)
            .build();
        Database.sensorDataMap.put("24e124128c067999-temperature", testSensorData);
        Database.modbusSensorMap.put("1-10", "24e124128c067999-temperature");

    }
    @Test
    void process() {

        Wire inputWire = new Wire();
        Wire outputWire = new Wire();

        ModbusMapper modbusMapper = new ModbusMapper("temp");
        modbusMapper.setInputWire(inputWire);
        modbusMapper.setOutputWire(0, outputWire);

        modbusMapper.start();

        ObjectNode payload = JsonNodeFactory.instance.objectNode();
        payload.put("_msgid", "b93a137f92c0b410");
        payload.put("topic", "polling");
        payload.put("unitId", 1);
        payload.put("address", 10);
        payload.put("data", 200);
        inputWire.put(new Message("polling", payload));


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JsonNode result = outputWire.get().getPayload();

        assertEquals("24e124128c067999-temperature", result.path("deviceId").asText());
        assertEquals("nhnacademy", result.path("site").asText());
        assertEquals("gyeongnam", result.path("branch").asText());
        assertEquals("class_a", result.path("place").asText());
        assertEquals(20.0, result.path("value").asDouble());

    }

}
