package com.nhnacademy.aiot.node;

import java.util.Queue;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import com.nhnacademy.aiot.Message;

public class MqttIn extends Node {

    private String topicFilter;
    MqttClient mqttClient;
    Queue<Message> innerQueue; 

    public MqttIn(String id, boolean hasInputPort, int outputPortCount, String clientId) {
        super(id, hasInputPort, outputPortCount);
        this.mqttClient = FlowGenerator.clientMap.get(clientId);
    }

    @Override
    protected void preprocess() {
        try {
            mqttClient.connect();
            mqttClient.subscribe(topicFilter, (topic, message) ->{
            
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        
    }


    @Override
    protected void process() {

    }

    @Override
    protected void postprocess() {

    }
}
