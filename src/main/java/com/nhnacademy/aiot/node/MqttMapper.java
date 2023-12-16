package com.nhnacademy.aiot.node;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.SensorData;
import com.nhnacademy.aiot.db.Database;

/**
 * MqttMapper
 */
public class MqttMapper extends Node {

    protected MqttMapper(String id, boolean hasInputPort, int outputPortCount) {
        super(id, hasInputPort, outputPortCount);
    }

    @Override
    protected void process() {
        if (!inputPort.hasMessage()) {
            return;
        }
        Message msg = inputPort.getMsg();
        ObjectNode payload = (ObjectNode) msg.getPayload();
        SensorData sensorData = Database.sensorDataMap.get(payload.path("deviceId").asText());
        payload.put("address", sensorData.getAddress());

        out(msg);

    }



}
