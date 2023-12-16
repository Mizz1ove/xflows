package com.nhnacademy.aiot.node;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MqttInTest {
    @Test
    void testPostprocess() {

    }

    @Test
    void testPreprocess() {
        MqttIn mqttIn = new MqttIn("nodetype", false, 0, "mqttclientid", "#");

        assertDoesNotThrow(() -> {mqttIn.preprocess();});

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

       // assertTrue(mqttIn.innerQueue.size() > 0);

    }

    @Test
    void testProcess() {

    }

    @Test
    void testConstructor() {
        MqttIn mqttIn = new MqttIn("nodetype", false, 0, "mqttclientid", "application/#");

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
