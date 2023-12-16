package com.nhnacademy.aiot.node;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.Wire;
import com.nhnacademy.aiot.db.Database;

public class RuleEngineTest {
    @Test
    void testProcess() throws InterruptedException {

        Database.sensorDataMap.get("24e124136d151547-temperature").setValue(500);

        Wire inWire = new Wire();
        Wire outWire = new Wire();

        ObjectNode node  =JsonNodeFactory.instance.objectNode()
                        .put("devEui", "24e124136d151547")
                        .put("sensorType", "temperature")
                        .put("temperature", 1888);
       inWire.put(new Message("", node));

        RuleEngine ruleEngine = new RuleEngine("1");
        ruleEngine.setInputWire(inWire);


        ruleEngine.start();

        Thread.sleep(3000);

        assertEquals(1888, Database.sensorDataMap.get("24e124136d151547-temperature").getValue());
    }
}
