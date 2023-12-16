package com.nhnacademy.aiot.node;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.JSONUtils;
import com.nhnacademy.aiot.Message;

import java.time.LocalDateTime;

public class MqttMessageGenerator extends Node {

    protected MqttMessageGenerator(String id, boolean hasInputPort, int outputPortCount) {
        super(id, hasInputPort, outputPortCount);
    }

    @Override
    public void process() {
        String topic;

        if (!inputPort.hasMessage()) return;

        Message message = inputPort.getMsg();

        LocalDateTime localDateTime = LocalDateTime.now();

        String timeString = JSONUtils.objectToJsonString(localDateTime);
        ObjectNode jsonNode = JsonNodeFactory.instance.objectNode();


        String site = message.getPayload().path("site").asText();
        String branch = message.getPayload().path("branch").asText();
        String place = message.getPayload().path("place").asText();
        String endPoint = message.getPayload().path("deviceId").asText();
        String[] arr = endPoint.split("-");
        endPoint = arr[1];
        String value = message.getPayload().path("value").asText();

        topic =  "data/s/" + site + "/b/" + branch + "/p/" + place + "/e/" + endPoint;

        jsonNode.put("time", timeString );
        jsonNode.put("value", value);

        // Todo : time + value;
        Message outMessage = new Message(topic,jsonNode);
        out(outMessage);

    }
}
