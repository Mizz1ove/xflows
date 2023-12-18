package com.nhnacademy.aiot.node;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.FlowGenerator;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.utils.JSONUtils;

import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;

@Log4j2
@EqualsAndHashCode
public class MqttIn extends Node {

    private String topicFilter;
    MqttClient mqttClient;
    Queue<Message> innerQueue;

    public MqttIn(String id, boolean hasInputPort, int outputPortCount, String clientId, String topicFilter) {
        super(id, hasInputPort, outputPortCount);
        this.mqttClient = FlowGenerator.clientMap.get(clientId);
        this.topicFilter = topicFilter;
        this.innerQueue = new LinkedList<>();
    }

    public MqttIn(ObjectNode objectNode){
        this(
            objectNode.path("id").asText(), 
            false,
            objectNode.path("wires").size(), 
            objectNode.path("client").asText(),
            objectNode.path("topic").asText());
    }

    @Override
    public void preprocess() {
        log.info("start node : " + name);
        startTime = LocalDateTime.now(); 
        try {
            mqttClient.connect();
            mqttClient.subscribe(topicFilter, (topic, message) ->{
                receivedMessageCount++;
                String payload = new String( message.getPayload());
                innerQueue.add(new Message("", JSONUtils.parseJson(payload)));
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void process() {
        if(innerQueue.isEmpty()){
            return;
        }
        out(innerQueue.poll());
    }


}
