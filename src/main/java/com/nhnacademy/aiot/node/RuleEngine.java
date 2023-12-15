package com.nhnacademy.aiot.node;

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
        Database.sensorDataMap.put(msg.getPayload().getSensorId(), msg.getPayload()); // 아닐수도

        // TODO MQTT outNode로 보내기
        out(msg);

        // TODO Modbus server register update
        // TODO 무슨레지스터인지 체크해서 저장 하는거 .
        int[] register = modbusServer.getRegister();
        register[addr] = msg.getPayload().getData();


    }

}
