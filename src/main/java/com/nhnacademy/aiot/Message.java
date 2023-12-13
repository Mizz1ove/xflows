package com.nhnacademy.aiot;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Message {
    String id;
    ObjectNode objectNode;
    
    public Message(String topic, MqttMessage message){

    }

    public Message(ObjectNode objectNode){
        this.objectNode = objectNode;
        this.id = UUID.randomUUID().toString();
    }
}
