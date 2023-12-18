package com.nhnacademy.aiot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.Socket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.node.FilterNode;
import com.nhnacademy.aiot.node.GenerateTopicNode;
import com.nhnacademy.aiot.node.ModbusMapper;
import com.nhnacademy.aiot.node.MqttIn;
import com.nhnacademy.aiot.node.MqttMapper;
import com.nhnacademy.aiot.node.MqttOutNode;
import com.nhnacademy.aiot.node.RuleEngine;
import com.nhnacademy.aiot.node.SplitNode;
import com.nhnacademy.aiot.node.modbus.ModBusReadNode;
import com.nhnacademy.aiot.node.modbus.ModbusClients;
import com.nhnacademy.aiot.node.modbus.ModbusServerNode;
import com.nhnacademy.aiot.utils.JSONUtils;

public class FlowGeneratorTest {
    @Test
    void testConnectWires() {

    }

    @Test
    @DisplayName("ObjectNode 생성자로 MqttIn 생성하기")
    void generateMqttIn() {
        MqttIn expected = new MqttIn("mqttIn", false, 1, "ems", "application/#");
            
        ObjectNode objectNode = JSONUtils.parseJson(" {\"id\" : \"mqttIn\", " +
            " \"nodeType\" : \"MqttIn\", " +
            " \"topic\" : \"application/#\", " +
            " \"qos\" : 1, " +
            " \"client\" : \"ems\", " +
            " \"wires\" : [ [\"3333\"]] }"
            );
        
        MqttIn actual = new MqttIn(objectNode);

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("ObjectNode 생성자로 Client 생성하기")
    void generateClient(){

    }

    @Test
    @DisplayName("ObjectNode 생성자로 FilterNode 생성하기")
    void generateFilterNode(){
        FilterNode expected = new FilterNode("filterNode", 1, new String[] {"devEui", "object"});

        ObjectNode objectNode = JSONUtils.parseJson(" {\"id\" : \"filterNode\", " +
            " \"nodeType\" : \"FilterNode\", " +
            " \"targetStrings\" : [\"devEui\", \"object\"], " +
            " \"wires\" : [[\"splitNode\"]] }");

        FilterNode actual = new FilterNode(objectNode);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ObjectNode 생성자로 SplitNode 생성하기")
    void generateSplitNode(){
        SplitNode expected = new SplitNode("splitNode", 1, "object", "sensorType");

        ObjectNode objectNode = JSONUtils.parseJson("{\"id\" : \"splitNode\"," +
            " \"nodeType\" : \"SplitNode\", " +
            " \"splitKey\" : \"object\", " +
            " \"keyHolder\" : \"sensorType\", " +
            " \"wires\" : [[\"splitNode\"]] }");

        SplitNode actual = new SplitNode(objectNode);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ObjectNode 생성자로 MqttMapper 생성하기")
    void generateMqttMapper(){
        MqttMapper expected = new MqttMapper("mqttMapper", true, 1);

        ObjectNode objectNode = JSONUtils.parseJson("{ \"id\" : \"mqttMapper\" , " +
            " \"nodeType\" : \"mqttMapper\" ," +
            " \"wires\" : [[\"ruleEngine\"]]}");
    
        MqttMapper actual = new MqttMapper(objectNode);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ObjectNode 생성자로 ModbusServerNode 생성하기")
    void generateModbusServerNode(){
        ModbusServerNode expected = new ModbusServerNode("modbusServer", 0);

        ObjectNode objectNode = JSONUtils.parseJson("{ \"id\" : \"modbusServer\" , " +
            " \"wires\" : [] }");
        
        ModbusServerNode actual = new ModbusServerNode(objectNode);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ObjectNode 생성자로 RuleEngine 생성하기")
    void generateRuleEngine(){
        RuleEngine expected = new RuleEngine("engine");

        ObjectNode objectNode = JSONUtils.parseJson("{ \"id\" : \"engine\" , \"wires\" : [[\"modbusServer\"], [\"generateTopic\"] ] }");
        
        RuleEngine actual = new RuleEngine(objectNode);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ObjectNode 생성자로 GenerateTopicNode 생성하기")
    void generateGenerateTopicNode(){
        GenerateTopicNode expected = new GenerateTopicNode("generateTopic", 1, "data/d/+/b/+/p/+/e/+", 
            new String[]{"devEui", "branch", "place", "sensorType"});

        ObjectNode objectNode = JSONUtils.parseJson("{ \"id\" : \"generateTopic\", " +
            " \"topicPattern\" : \"data/d/+/b/+/p/+/e/+\" ," +
            " \"field\" : [\"devEui\", \"branch\", \"place\", \"sensorType\" ]," +
            " \"wires\" : [[\"mqttOut\"]] }" );


        GenerateTopicNode actual = new GenerateTopicNode(objectNode);

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("ObjectNode 생성자로 MqttOutNode 생성하기")
    void generateMqttOutNode(){
        MqttOutNode expected = new MqttOutNode("mqttOut", "localhost", 0);

        ObjectNode objectNode = JSONUtils.parseJson(" {\"id\" : \"mqttOut\", " +
        " \"nodeType\" : \"MqttOutNode\", " +
        " \"qos\" : 1, " +
        " \"client\" : \"localhost\", " +
        " \"wires\" : [] }"
        );

        MqttOutNode actual = new MqttOutNode(objectNode);

        assertEquals(expected, actual);

    }


    // TODO : ModbusReadNode가 맞음
    @Test
    @DisplayName("ObjectNode로 ModBusReadNode 생성하기")
    void generateModBusReadNode(){

        ModbusClients.clientMap.put("node-red1", new Socket());

        ModBusReadNode expected = new ModBusReadNode("modbusReadNode", false, 1, "node-red1", 0);

        ObjectNode objectNode = JSONUtils.parseJson("{ \"id\" : \"modbusReadNode\", " +
            " \"client\" : \"node-red1\", " +
            " \"address\" : 0 , " +
            " \"wires\" : [[\"뭐더라?\"]] } "
        );

        ModBusReadNode actual = new ModBusReadNode(objectNode);

        assertEquals(expected, actual);
    
    }

    @Test
    @DisplayName("ObjectNode로 ModbusMapper 생성하기")
    void generateModbusMapper(){
        ModbusMapper expected = new ModbusMapper("modbusMapper");

        ObjectNode objectNode = JSONUtils.parseJson("{ \"id\": \"modbusMapper\" , \"wires\" : [[\"ruleEngine\"]]}");

        ModbusMapper actual = new ModbusMapper(objectNode);
        assertEquals(expected, actual);

    }


    @Test
    void testGenerateOutputWires() {

    }

    @Test
    void testInjectClients() {

    }

    @Test
    void testStart() {

    }
}
