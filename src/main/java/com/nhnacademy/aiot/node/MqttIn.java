package com.nhnacademy.aiot.node;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttIn extends Node {

    private String topicFilter;
    MqttClient mqttClient;

    public MqttIn(String id, boolean hasInputPort, int outputPortCount, String clientId) {
        super(id, hasInputPort, outputPortCount);
        this.mqttClient = FlowGenerator.clientMap.get(clientId);
    }

    @Override
    protected void preprocess() throws MqttException {
        mqttClient.connect();
        mqttClient.subscribe(topicFilter, (topic, message) ->{

        });
    }

    @Override
    protected void process() {

    }

    @Override
    protected void postprocess() {

    }
}
