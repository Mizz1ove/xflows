package com.nhnacademy.aiot.node;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.SensorData;
import com.nhnacademy.aiot.db.Database;

import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;

@Log4j2
@EqualsAndHashCode
public class ModbusMapper extends Node {

    public ModbusMapper(String id) {
        super(id, true, 1);
    }

    public ModbusMapper(String id, int outputPortCount){
        super(id, true, outputPortCount);
    }

    public ModbusMapper(ObjectNode objectNode){
        this(objectNode.path("id").asText(), objectNode.path("wires").size());
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
        String sensorType = sensorId.split("-")[1];
        // TODO sensorData null이면 예외처리

        ObjectNode payload = msg.getPayload();

        payload.put("devEui", sensorId);
        payload.put("site", sensorData.getSite());
        payload.put("branch", sensorData.getBranch());
        payload.put("place", sensorData.getPlace());
        payload.put("sensorType", sensorType);

        double ratio = sensorData.getRatio();
        payload.put(sensorType, payload.path("data").asInt() * ratio ); // 정수 int 값에 곱하기 비율 -> actual Value
        return msg;
    }

}

