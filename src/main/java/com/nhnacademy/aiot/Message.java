package com.nhnacademy.aiot;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Message {
    String id;
    ObjectNode objectNode;
    ObjectMapper objectMapper = new ObjectMapper();

    public Message(String topic, MqttMessage message){

        String payload =  new String(message.getPayload());

    }

    public Message(ObjectNode objectNode){
        this.objectNode = objectNode;
        this.id = UUID.randomUUID().toString();
    }


    // Todo : 원하는 value 를 String 으로 return
    public JsonNode PhasePath(String paths, String payload) {

        String str = payload;
        JsonNode jsonNode = JSONUtils.parseJson(str);

        String[] arr = getPhasePath(paths);
        for (int i = 0; i < arr.length; i++) {
            jsonNode = jsonNode.path(arr[i]);
        }
        return jsonNode;

    }

    // Todo : "."을 포함한 String으로 받아서 배열로 return
    public String[] getPhasePath(String paths) {
        String[] pathArr = paths.split("\\.");
        return pathArr;
    }

}
