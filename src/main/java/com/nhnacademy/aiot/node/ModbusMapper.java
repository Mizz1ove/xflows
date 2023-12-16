package com.nhnacademy.aiot.node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Database;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.SensorData;

public class ModbusMapper extends Node {

    private double ratio;

    ModbusMapper(String id) {
        super(id, true, 1);
    }

    @Override
    public void preprocess() {

    }

    @Override
    public void process() {
        if (!inputPort.hasMessage()) {
            return;
        }

        Message msg = inputPort.getMsg();
        Message modifiedMsg = addInfo(msg);
        out(modifiedMsg);

    }

    @Override
    public void postprocess() {

    }

    private Message addInfo(Message msg){

        // TODO : payloadID 만들기
        String sensorId = Database.modbusSensorMap.get("");
        SensorData sensorData = Database.sensorDataMap.get(sensorId);
        // TODO sensorData null이면 예외처리

        ObjectNode payload = (ObjectNode) msg.getPayload();

        payload.put("deviceId", sensorId);
        payload.put("site", sensorData.getSite());
        payload.put("branch", sensorData.getBranch());
        payload.put("place", sensorData.getPlace());

        payload.put("value", payload.path("data").asInt() * ratio ); // 정수 int 값에 곱하기 비율 -> actual Value

        return msg;
    }

}
