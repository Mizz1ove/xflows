package com.nhnacademy.aiot.node;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.util.HashMap;
import java.util.Map;

public class FlowGenerator {

    public static final Map<String, MqttClient> clientMap = new HashMap<>();

    static {
        try {
            clientMap.put("ems", new MqttClient("tcp://ems.nhnacademy.com", "ems"));
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    
}
