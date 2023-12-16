package com.nhnacademy.aiot.node;

import java.util.LinkedList;
import java.util.Queue;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.JSONUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.Msg;

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

    @Override
    public void preprocess() {

        try {
            mqttClient.connect();
            mqttClient.subscribe(topicFilter, (topic, message) ->{
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
        Message msg = innerQueue.poll();
    }

    @Override
    protected void postprocess() {

    }
}
