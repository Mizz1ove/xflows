package com.nhnacademy.aiot.node;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.Wire;
import static org.junit.jupiter.api.Assertions.*;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

class NodeTest {

    @Test
    void out() {
    }

    @Test
    void testOut() {
    }

    @Test
    void bindInputWire() {
    }

    @Test
    void bindOutputWire() {
    }

    @Test
    void run() {
    }

    @Test
    void start() {
    }

    @Test
    @DisplayName("수신 메시지 횟수 확인")
    void testhasReceivedMsg() throws InterruptedException {
        Wire wire = new Wire();

        wire.put(new Message("", JsonNodeFactory.instance.objectNode().put("deviceId", "24e124136d151547-humidity")));
        
        MqttMapper mqttMapper = new MqttMapper("mqttmapper", true, 0);
        mqttMapper.setInputWire(wire);
        mqttMapper.start();

        Thread.sleep(2000);

        assertTrue(mqttMapper.getMsgReceivedCount() > 0);

    }

    @Test
    @DisplayName("송신 메시지 횟수 확인")
    void testhasSendMsg() throws InterruptedException {
        Wire wire = new Wire();
        Wire outWire = new Wire();

        wire.put(new Message("", JsonNodeFactory.instance.objectNode().put("deviceId", "24e124136d151547-humidity")));
        
        MqttMapper mqttMapper = new MqttMapper("mqttmapper", true, 1);
        mqttMapper.setInputWire(wire);
        mqttMapper.setOutputWire(0, outWire);
        mqttMapper.start();

        Thread.sleep(2000);


        assertTrue(mqttMapper.getMsgSendCount() > 0);
    }

}
