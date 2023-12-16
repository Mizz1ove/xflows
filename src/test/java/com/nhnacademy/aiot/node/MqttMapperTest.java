package com.nhnacademy.aiot.node;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.nhnacademy.aiot.Msg;
import com.nhnacademy.aiot.Wire;

class MqttMapperTest {
    @Test
    void testProcess() throws InterruptedException {

        Wire wire = new Wire();
        Wire outWire = new Wire();
        wire.put(new Msg("", JsonNodeFactory.instance.objectNode().put("deviceId", "24e124136d151547-humidity")));

        MqttMapper mqttMapper = new MqttMapper(null, true, 1);
        mqttMapper.bindInputWire(wire);
        mqttMapper.bindOutputWire(outWire, 0);
        mqttMapper.start();
        
        Thread.sleep(2000);

        Msg msg = outWire.get();
        assertAll(()-> {
            assertEquals( 1, msg.getPayload().get("address").asInt());
        });
    }
}
