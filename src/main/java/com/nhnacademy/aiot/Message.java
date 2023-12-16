package com.nhnacademy.aiot;

import java.util.UUID;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;


public class Message {

    @Setter
    @Getter
    private String topic;
    private final String msgId;
    @Getter
    @Setter
    private JsonNode payload;

    public Message(String topic, JsonNode payload) {
        this.topic = topic;
        this.payload = payload;
        long createTime = System.currentTimeMillis();
        this.msgId = UUID.randomUUID().toString();

        ObjectNode objectNode = (ObjectNode)payload;
        objectNode.put("time", createTime);
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
