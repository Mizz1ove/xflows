package com.nhnacademy.aiot.node;

import com.nhnacademy.aiot.db.Database;
import com.nhnacademy.aiot.node.modbus.ModbusServerNode;
import java.util.Objects;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.SensorData;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RuleEngine extends Node {

    RuleEngine(String id){
        super(id, true, 2);
    }

    @Override
    public void process() {

        if (!inputPort.hasMessage()){
            return;
        }
            
        Message msg = inputPort.getMsg();

        if(msg.getPayload().has("data")){
            modBusMessage(msg);
        }else {
            mqttMessage(msg);
        }

    }

    private void mqttMessage(Message msg) {
         String deviceId = msg.getPayload().path("devEui").asText();
        String sensorType = msg.getPayload().path("sensorType").asText();
        double value = msg.getPayload().path(sensorType).asDouble();
        SensorData sensorData = Database.sensorDataMap.get(deviceId+"-"+sensorType);
        int address  = msg.getPayload().path("address").asInt();
        if (Objects.isNull(sensorData)) {
            return;
        }
        sensorData.setValue(value);
        ModbusServerNode.inputRegisters[address]= (int)(value * 10);
        ObjectNode payload = msg.getPayload();
        payload.put("branch", sensorData.getBranch());
        payload.put("place", sensorData.getPlace());
        payload.put("value", sensorData.getValue());
        log.debug("MQTTTT "+msg);
        out(msg);
    }


    private void modBusMessage(Message msg) {
        int address  = msg.getPayload().path("address").asInt();
        double value = msg.getPayload().path("data").asDouble();
        ModbusServerNode.inputRegisters[address] = (int)(value);
        System.out.println(value + "인풋레지스터값" + ModbusServerNode.inputRegisters[address]);
         log.debug("MODBUS "+msg);
        out(msg);

    }
    
}
