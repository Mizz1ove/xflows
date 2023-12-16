package com.nhnacademy.aiot.node;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.Wire;

class MqttMapperTest {
    @Test
    void testProcess() throws InterruptedException {

        Wire wire = new Wire();
        Wire outWire = new Wire();
        wire.put(new Message("", JsonNodeFactory.instance.objectNode().put("deviceId", "24e124136d151547-humidity")));

        MqttMapper mqttMapper = new MqttMapper(null, true, 1);
        mqttMapper.setInputWire(wire);
        mqttMapper.setOutputWire(0, outWire);
        mqttMapper.start();
        
        Thread.sleep(2000);

        Message msg = outWire.get();
        assertAll(()-> {
            assertEquals( 1, msg.getPayload().get("address").asInt());
        });
    }
}
