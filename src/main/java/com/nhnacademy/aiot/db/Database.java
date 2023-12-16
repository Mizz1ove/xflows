package com.nhnacademy.aiot.db;

import java.util.HashMap;
import java.util.Map;
import com.nhnacademy.aiot.SensorData;

public class Database {

    public static final Map<String, SensorData> sensorDataMap = new HashMap<>();
    public static final Map<String,String> modbusSensorMap = new HashMap<>();

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


         // CLASS_A   
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

        SensorData sensorData9 = SensorData.builder()
            .deviceId("24e124785c389818-temperature")
            .branch("gyeongnam")
            .place("class_a")
            .register("input")
            .address(107)
            .build();  
    

        SensorData sensorData5 = SensorData.builder()
            .deviceId("24e124785c421885-humidity")
            .branch("gyeongnam")
            .place("class_a")
            .register("input")
            .address(202)
            .build();

        SensorData sensorData6 = SensorData.builder()
            .deviceId("24e124785c421885-temperature")
            .branch("gyeongnam")
            .place("class_a")
            .register("input")
            .address(103)
            .build();        

        // SERVER ROOM
        SensorData sensorData7 = SensorData.builder()
            .deviceId("24e124136d151368-temperature")
            .branch("gyeongnam")
            .place("server_room")
            .register("input")
            .address(104)
            .build();        
           
        // CLASS B

        SensorData sensorData8 = SensorData.builder()
            .deviceId("24e124128c140101-temperature")
            .branch("gyeongnam")
            .place("class_b")
            .register("input")
            .address(105)
            .build();  

        //LOBBY
        SensorData sensorData10 = SensorData.builder()
            .deviceId("24e124785c389010-temperature")
            .branch("gyeongnam")
            .place("lobby")
            .register("input")
            .address(106)
            .build();  



        sensorDataMap.put(sensorData.getDeviceId(), sensorData);
        sensorDataMap.put(sensorData2.getDeviceId(), sensorData2);
        sensorDataMap.put(sensorData3.getDeviceId(), sensorData3);
        sensorDataMap.put(sensorData4.getDeviceId(), sensorData4);
        sensorDataMap.put(sensorData5.getDeviceId(), sensorData5);
        sensorDataMap.put(sensorData6.getDeviceId(), sensorData6);
        sensorDataMap.put(sensorData7.getDeviceId(), sensorData6);
        sensorDataMap.put(sensorData8.getDeviceId(), sensorData6);
        sensorDataMap.put(sensorData9.getDeviceId(), sensorData6);
        sensorDataMap.put(sensorData10.getDeviceId(), sensorData6);

        modbusSensorMap.put("1-3-1", "1234-temperature");
    }
}
