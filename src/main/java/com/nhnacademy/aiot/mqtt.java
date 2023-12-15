package com.nhnacademy.aiot;

import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.paho.client.mqttv3.*;

import java.io.*;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class mqtt {
    private static final String site = "ems.nhnacademy.com";
    private static final int port = 1883; // mqtt
    // modbus 가 502

    public static void main(String[] args) throws IOException {
        String publisherId = UUID.randomUUID().toString();
        try (IMqttClient client = new MqttClient("tcp://ems.nhnacademy.com:1883", publisherId)) {

            client.connect();

            CountDownLatch receivedSignal = new CountDownLatch(50);

            IMqttMessageListener listener = (topic, msg) -> {

                String payload = new String(msg.getPayload());
                System.out.println(payload.toString());


                String str = payload.toString();
                JsonNode jsonNode = JSONUtils.parseJson(str);

                String devEui =  jsonNode.path("deviceInfo").path("devEui").asText();

                System.out.println("get " + jsonNode.get("deviceInfo"));


            };
            String[] topics = {"+/+/device/+/+/up","+/+/device/+/+/up"}; // 여러 토픽
            client.subscribe( topics , new IMqttMessageListener[] {listener,listener} );
            receivedSignal.await(1, TimeUnit.MINUTES);
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}

