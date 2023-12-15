package com.nhnacademy.aiot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import com.nhnacademy.aiot.node.MqttIn;

public class PortTest {
    @Test
    void testBindWire() {

    }

    @Test
    void testOut() {
        MqttIn mqttIn = new MqttIn("nodetype", false, 1, "mqttclientid", "application/#");
        MqttIn mqtt = new MqttIn("datatype", true, 0, "1234", "lrhalg");
        Wire wire = new Wire();
        mqttIn.bindOutputWire(wire,0);
        mqtt.bindInputWire(wire);

        assertNotNull(wire);
    }
}
