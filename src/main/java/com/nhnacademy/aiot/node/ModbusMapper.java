package com.nhnacademy.aiot.node;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.SensorData;
import com.nhnacademy.aiot.db.Database;

public class ModbusMapper extends Node {

    ModbusMapper(String id) {
        super(id, true, 1);
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


    private Message addInfo(Message msg){

        String unitId = msg.getPayload().path("unitId").asText();
        String address = msg.getPayload().path("address").asText();
        String sensorId = Database.modbusSensorMap.get(unitId+"-"+address);
        SensorData sensorData = Database.sensorDataMap.get(sensorId);
        // TODO sensorData null이면 예외처리

        ObjectNode payload = (ObjectNode) msg.getPayload();

        payload.put("deviceId", sensorId);
        payload.put("site", sensorData.getSite());
        payload.put("branch", sensorData.getBranch());
        payload.put("place", sensorData.getPlace());

        double ratio = sensorData.getRatio();
        payload.put("value", payload.path("data").asInt() * ratio ); // 정수 int 값에 곱하기 비율 -> actual Value

        return msg;
    }

}

