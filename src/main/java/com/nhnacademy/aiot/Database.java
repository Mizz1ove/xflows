package com.nhnacademy.aiot;

import java.util.HashMap;
import java.util.Map;

public class Database {

    public static Map<String, SensorData> sensorDataMap = new HashMap<>();
    public static Map<String,String> modbusSensorMap = new HashMap<>();

    static {
        SensorData sensorData = SensorData.builder()
            .deviceId("24e124136d151547-humidity")
            .branch("gyeongnam")
            .place("storage")
            .register("input")
            .address(1).build();

        SensorData sensorData2 = SensorData.builder()
            .deviceId("24e124136d151547-temperature")
            .branch("gyeongnam")
            .place("storage")
            .register("input")
            .address(2)
            .build();

        SensorData sensorData3 = SensorData.builder()
            .deviceId("24e124128c067999-temperature")
            .branch("gyeongnam")
            .place("class_a")
            .register("input")
            .address(102)
            .build();

        SensorData sensorData4 = SensorData.builder()
            .deviceId("24e124785c389818-humidity")
            .branch("gyeongnam")
            .place("class_a")
            .register("input")
            .address(201)
            .build();

        SensorData sensorData5 = SensorData.builder()
            .deviceId("24e124785c421885-humidity")
            .branch("gyeongnam")
            .place("class_a")
            .register("input")
            .address(202)
            .build();

        sensorDataMap.put(sensorData.getDeviceId(), sensorData);
        sensorDataMap.put(sensorData2.getDeviceId(), sensorData2);
        sensorDataMap.put(sensorData3.getDeviceId(), sensorData3);
        sensorDataMap.put(sensorData4.getDeviceId(), sensorData4);
        sensorDataMap.put(sensorData5.getDeviceId(), sensorData5);
        modbusSensorMap.put("1-3-1", "1234-temperature");
    }
}
