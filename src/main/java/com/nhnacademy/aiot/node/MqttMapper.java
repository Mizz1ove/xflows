package com.nhnacademy.aiot.node;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.SensorData;
import com.nhnacademy.aiot.db.Database;
import lombok.extern.log4j.Log4j2;

/**
 * MqttMapper
 */
@Log4j2
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
        String deviceId = msg.getPayload().path("devEui").asText();
        String sensorType = msg.getPayload().path("sensorType").asText();
        try {
            SensorData sensorData = Database.sensorDataMap.get(deviceId + "-" + sensorType);
            payload.put("address", sensorData.getAddress());
            out(msg);
        } catch (NullPointerException e) {

        }



    }



}
