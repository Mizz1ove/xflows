package com.nhnacademy.aiot;

import com.nhnacademy.aiot.FlowGenerator;
import com.nhnacademy.aiot.node.MqttIn;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        try {
            FlowGenerator.clientMap.put("mqttclientid", new MqttClient("tcp://ems.nhnacademy.com:1883", "nodetype"));
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        MqttIn mqttIn = new MqttIn("nodetype", false, 0, "mqttclientid", "#");
        mqttIn.preprocess();
    }
}
