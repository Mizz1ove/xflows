package com.nhnacademy.aiot.node;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Message;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class GenerateTopicNode extends Node {

    private static final String NODE_ID = "id";
    private static final String WIRES = "wires";
    private static final String TOPIC_PATTERN = "topicPattern";
    private static final String FIELD = "field";

    private String topicPattern;
    private String[] fields;

    public GenerateTopicNode(String id, int outputPortCount, String topicPattern, String[] fields) {
        super(id, outputPortCount);
        this.topicPattern = topicPattern;
        this.fields = fields;
    }

    public GenerateTopicNode(ObjectNode objectNode) {
        this(objectNode.path(NODE_ID).asText(),
                objectNode.path(WIRES).size(),
                objectNode.path(TOPIC_PATTERN).asText(),
                StreamSupport.stream(objectNode.path(FIELD).spliterator(), false)
                        .map(JsonNode::asText)
                        .toArray(String[]::new));
    }

    @Override
    public void process() {
        if (!inputPort.hasMessage())
            return;
       

        Message msg = inputPort.getMsg();
        JsonNode inPayload = msg.getPayload();
        String generatedTopic = generate(inPayload);
        if (generatedTopic == null)
            return;
        msg.setTopic(generatedTopic);
        out(msg);

    }

    private String generate(JsonNode jsonNode) {
        String newTopic = topicPattern;
        for (String field : fields) {
            if (jsonNode.path(field).isMissingNode()) {
                return null;
            }
            newTopic = newTopic.replaceFirst("\\+", jsonNode.path(field).asText());
        }
        return newTopic;
    }
}
