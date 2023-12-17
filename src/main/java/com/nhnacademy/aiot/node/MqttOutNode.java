package com.nhnacademy.aiot.node;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.nhnacademy.aiot.FlowGenerator;
import com.nhnacademy.aiot.Message;

import lombok.extern.log4j.Log4j2;

public class MqttOutNode extends Node {

    private static final String NODE_ID = "id";
    private static final String WIRES = "wires";
    private static final String CLIENT_ID = "clientId";

    private MqttClient mqttClient;

    public MqttOutNode(String id, String clientId, int outputWireCount) {
        super(id,outputWireCount);
        mqttClient = FlowGenerator.clientMap.get(clientId);
    }

    
    public MqttOutNode(String id) {
        super(id, 0);
        
    }

    public MqttOutNode(JsonNode jsonNode){
        this(jsonNode.path(NODE_ID).asText(), jsonNode.path(CLIENT_ID).asText(), jsonNode.path(WIRES).size());
    }

    @Override
    protected void preprocess() {
        try {
            mqttClient.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process() {

        if (inputPort.hasMessage()) {
            Message msg = inputPort.getMsg();
            try {
                mqttClient.publish(msg.getTopic(), new MqttMessage(msg.getPayload().toString().getBytes()));
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void postprocess() {
        try {
            mqttClient.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }



}
