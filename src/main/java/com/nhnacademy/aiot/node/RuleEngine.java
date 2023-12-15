package com.nhnacademy.aiot.node;

import com.nhnacademy.aiot.Database;
import com.nhnacademy.aiot.Msg;
import com.nhnacademy.aiot.node.Node;

public class RuleEngine extends Node {

    ModbusServer modbusServer; // TODO set

    RuleEngine(String id){
        super(id, true, 2);
    }

    @Override
    public void preprocess() {

    }

    @Override
    public void process() {

        if (!inputPort.hasMessage())
            return;

        Msg msg = inputPort.getMsg();

        // TODO db에 저장
        String deviceId = msg.getPayload().path("deviceId").asText();
        double value = msg.getPayload().path("value").asDouble(); // TODO String 타입이 아닌가?
        Database.sensorDataMap.get(deviceId).builder().value(value).build();

        // TODO MQTT outNode로 보내기
        out(msg);

        // TODO Modbus server register update
        // TODO 무슨레지스터인지 체크해서 저장 해야함 ..
        int[] register = modbusServer.getRegister();
        register[addr] = msg.getPayload().getData();


    }

    @Override
    protected void postprocess() {

    }
}
