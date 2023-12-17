package com.nhnacademy.aiot;

import java.util.UUID;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.utils.JSONUtils;


public class Message {

    private String topic;
    private long createTime;
    private String msgId;
    private ObjectNode payload;

    public Message(String topic, ObjectNode payload) {
        this.topic = topic;
        this.payload = payload;
        this.createTime = System.currentTimeMillis();
        this.msgId = UUID.randomUUID().toString();

        ObjectNode objectNode = payload;
        objectNode.put("time", createTime);
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setPayload(ObjectNode payload) {
        this.payload = payload;
    }

    public ObjectNode getPayload() {
        return payload;
    }

    public String getTopic() {
        return topic;
    }

    public JsonNode getJSON() {

        return JSONUtils.parseJson(this.toString());
    }

    @Override
    public String toString() {

        return "{" + "\"topic\" : \"" + topic + "\", " +
            "\"msgId\" : \"" + msgId + "\", " +
            "\"payload\" : " + payload + "}";

    }
}
