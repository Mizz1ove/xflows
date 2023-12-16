package com.nhnacademy.aiot.node;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Database;
import com.nhnacademy.aiot.Msg;
import com.nhnacademy.aiot.SensorData;

public class ModbusMapper extends Node {

    ModbusMapper(String id) {
        super(id, true, 1);
    }

    @Override
    protected void preprocess() {

    }

    @Override
    public void process() {
        if (!inputPort.hasMessage()) {
            return;
        }

        Msg msg = inputPort.getMsg();
        //Msg modifiedMsg = addInfo(msg);
       // out(modifiedMsg);

    }

    @Override
    protected void postprocess() {

    }

    // private Msg addInfo(Msg msg){

    //     String sensorId = Database.modbusSensorMap.get(페이로드에서 아이디 만들기);
    //     SensorData sensorData = Database.sensorDataMap.get(sensorId);
    //     // TODO sensorData null이면 예외처리

    //     ObjectNode payload = msg.getPayload();

    //     payload.put("deviceId", sensorId);
    //     payload.put("site", sensorData.getSite());
    //     payload.put("branch", sensorData.getBranch());
    //     payload.put("place", sensorData.getPlace());
    //     payload.put("value", payload.path("data") * ratio);

    //     return msg;
    // }

}
