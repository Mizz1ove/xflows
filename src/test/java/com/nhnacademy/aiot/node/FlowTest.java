package com.nhnacademy.aiot.node;

import com.nhnacademy.aiot.Wire;

public class FlowTest {
    
    public static void main(String[] args) {
        MqttIn mqttIn = new MqttIn("mqttIn", false, 1, "ems", "application/#");
        FilterNode filterNode = new FilterNode("filterNode", 1, new String[] {"devEui", "object"});
        SplitNode splitNode = new SplitNode("splitNode", 1, "object", "sensorType");
       // ReplaceNode replaceNode = new ReplaceNode("replaceNode", 1,  new String[] {"humidity", "temperature", "co2", "tvoc"}, "value");
        MqttMapper mqttMapper = new MqttMapper("mqttMapper", true, 1);

        Wire mqttInToFilter = new Wire();
        mqttIn.setOutputWire(0, mqttInToFilter);
        filterNode.setInputWire(mqttInToFilter);

        Wire filterToSplit = new Wire();
        filterNode.setOutputWire(0, filterToSplit);
        splitNode.setInputWire(filterToSplit);

        Wire splitToReplace = new Wire();
        splitNode.setOutputWire(0, splitToReplace);
        mqttMapper.setInputWire(splitToReplace);
        //replaceNode.setInputWire(splitToReplace);

        // Wire replaceToMqttMapper = new Wire();
        // replaceNode.setOutputWire(0, replaceToMqttMapper);
        // mqttMapper.setInputWire(replaceToMqttMapper);
        
        mqttIn.start();
        filterNode.start();
        splitNode.start();
     //   replaceNode.start();
        mqttMapper.start();
    }
}
