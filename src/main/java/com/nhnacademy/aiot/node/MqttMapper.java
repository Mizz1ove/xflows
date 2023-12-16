package com.nhnacademy.aiot.node;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Database;
import com.nhnacademy.aiot.Msg;
import com.nhnacademy.aiot.SensorData;

/**
 * MqttMapper
 */
public class MqttMapper extends Node {

    protected MqttMapper(String id, boolean hasInputPort, int outputPortCount) {
        super(id, hasInputPort, outputPortCount);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void preprocess() {
    }

    @Override
    protected void process() {
        if (!inputPort.hasMessage()) {
            return;
        }
        Msg msg = inputPort.getMsg();
        ObjectNode payload = (ObjectNode) msg.getPayload();
        SensorData sensorData = Database.sensorDataMap.get(payload.path("deviceId").asText());
        payload.put("address", sensorData.getAddress());

        out(msg);

    }

    @Override
    protected void postprocess() {}


}
