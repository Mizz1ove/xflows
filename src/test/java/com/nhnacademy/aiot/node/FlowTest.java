package com.nhnacademy.aiot.node;

import com.nhnacademy.aiot.FlowGenerator;
import com.nhnacademy.aiot.Wire;
import com.nhnacademy.aiot.node.modbus.ModBusReadNode;
import com.nhnacademy.aiot.node.modbus.ModbusServerNode;

public class FlowTest {
    
    public static void main(String[] args) {

        Runtime r = Runtime.getRuntime();
        
        r.addShutdownHook(new Thread(() -> FlowGenerator.nodeMap.values().stream().map(node -> (Node) node).forEach(Node::stop)));
        MqttIn mqttIn = new MqttIn("mqttIn", false, 1, "ems", "application/#");
        FilterNode filterNode = new FilterNode("filterNode", 1, new String[] {"devEui", "object"});
        SplitNode splitNode = new SplitNode("splitNode", 1, "object", "sensorType");
        MqttMapper mqttMapper = new MqttMapper("mqttMapper", true, 1);
        ModbusServerNode modbusServer = new ModbusServerNode("modbusServer", 0);
        RuleEngine ruleEngine = new RuleEngine("engine");
        GenerateTopicNode generateTopicNode = new GenerateTopicNode("generateTopic", 1, "data/d/+/b/+/p/+/e/+", new String[]{"devEui", "branch", "place", "sensorType"});
        FilterNode filterNode2 = new FilterNode("filterNode2", 1, new String[] {"time", "value"});
        MqttOutNode mqttOutNode = new MqttOutNode("mqttOut", "localhost", 0);

        ModBusReadNode modBusReadNode = new ModBusReadNode("modbusReadNode", false, 1, "node-red1", 0);
        ModbusMapper modbusMapper = new ModbusMapper("modbusMapper");

        Wire modReadToModMapper = new Wire();
        modBusReadNode.setOutputWire(0, modReadToModMapper);
        modbusMapper.setInputWire(modReadToModMapper);

        Wire modMapperToRuleEngine = new Wire();
        modbusMapper.setOutputWire(0, modMapperToRuleEngine);
        ruleEngine.setInputWire(modMapperToRuleEngine);


        modBusReadNode.start();
        modbusMapper.start();
        modbusServer.start();

        // MQTT 라인
        Wire mqttInToFilter = new Wire();
        mqttIn.setOutputWire(0, mqttInToFilter);
        filterNode.setInputWire(mqttInToFilter);

        Wire filterToSplit = new Wire();
        filterNode.setOutputWire(0, filterToSplit);
        splitNode.setInputWire(filterToSplit);

        Wire splitToMqttMapper = new Wire();
        splitNode.setOutputWire(0, splitToMqttMapper);
        mqttMapper.setInputWire(splitToMqttMapper);

        Wire mqttMapperToEngine = new Wire();
        mqttMapper.setOutputWire(0,mqttMapperToEngine);
        ruleEngine.setInputWire(mqttMapperToEngine);
        // mqttMapper.setInputWire(replaceToMqttMapper);

        Wire engienToTopic = new Wire();
        ruleEngine.setOutputWire(0, engienToTopic);
        generateTopicNode.setInputWire(engienToTopic);

        Wire topicToFilter = new Wire();
        generateTopicNode.setOutputWire(0, topicToFilter);
        filterNode2.setInputWire(topicToFilter);

        Wire filterToMqttOut = new Wire();
        filterNode2.setOutputWire(0, filterToMqttOut);
        mqttOutNode.setInputWire(filterToMqttOut);
        
        DebugNode topicDebuger = new DebugNode("topicDebuger");
        Wire topicDebugWire= new Wire();
        generateTopicNode.setOutputWire(0, topicDebugWire);
        topicDebuger.setInputWire(topicDebugWire);

        DebugNode filter2Debuger = new DebugNode("filter2Debuger");
        Wire filter2DebugWire= new Wire();
        filterNode2.setOutputWire(0, filter2DebugWire);
        filter2Debuger.setInputWire(filter2DebugWire);

        mqttIn.start();
        filterNode.start();
        splitNode.start();
        mqttMapper.start();
        ruleEngine.start();
        generateTopicNode.start();
        filterNode2.start();
        mqttOutNode.start();
        
        topicDebuger.start();
        //filter2Debuger.start();
    }
}
