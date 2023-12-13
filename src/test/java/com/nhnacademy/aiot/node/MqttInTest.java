package com.nhnacademy.aiot.node;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MqttInTest {
    @Test
    void testPostprocess() {

    }

    @Test
    void testPreprocess() {

    }

    @Test
    void testProcess() {

    }

    @Test
    void testConstructor() {
        MqttIn mqttIn = new MqttIn("nodetype", false, 0, "mqttclientid");

        assertNotNull(mqttIn.mqttClient);
    }

    @BeforeEach
    void setUp(){
        try {
            FlowGenerator.clientMap.put("mqttclientid", new MqttClient("tcp://ems.nhnacademy.com:1883", "nodetype"));
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
