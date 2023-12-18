package com.nhnacademy.aiot.node;

import java.util.Iterator;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Message;

import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;

@Log4j2
@EqualsAndHashCode
public class SplitNode extends Node {

    private static final String NODE_ID = "id";
    private static final String WIRES = "wires";
    private static final String SPLIT_KEY = "splitKey";
    private static final String KEY_HOLDER = "keyHolder";
    private static final String DEFAULT_KEY_HOLDER = "";
    
    private String keyHolder;
    private String splitKey;
    //private boolean keep Msg 구현해야됨

    public SplitNode(String id, int outputPortCount, String splitKey, String keyHolder) {
        super(id, outputPortCount);
        this.splitKey = splitKey;
        this.keyHolder = keyHolder;
       
    }

    public SplitNode(String id, int outputPortCount, String splitKey) {
        this( id, outputPortCount, splitKey , DEFAULT_KEY_HOLDER);
    }

    public SplitNode(ObjectNode objectNode){
        this(objectNode.path(NODE_ID).asText(), objectNode.path(WIRES).size(), objectNode.path(SPLIT_KEY).asText() , objectNode.path(KEY_HOLDER).asText());
    }

    @Override
    public void process() {
        
        if(!inputPort.hasMessage()) return;
            
        JsonNode inputMsg = inputPort.getMsg().getPayload();
        Message msg = spliter(inputMsg, splitKey);
        if (msg != null){
            out(msg);
        }
        
    }

    private Message spliter(JsonNode payload, String key) {
        try {
            if (!payload.has(key)) {
                return null;
            }
            JsonNode objectNode = payload.get(key);

            if (objectNode.isObject()) {
                Iterator<Map.Entry<String, JsonNode>> fieldsIterator = objectNode.fields();
                while (fieldsIterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fieldsIterator.next();
                    String fieldName = entry.getKey();
                    JsonNode fieldValue = entry.getValue();

                    return createMsg(payload, key, fieldName, fieldValue);
                }
            }

        } catch (Exception e) {
           log.error(e.getMessage());
        }

        return null;
    }

    private Message createMsg(JsonNode payload, String key, String fieldName, JsonNode fieldValue) {
        ObjectNode jsonNode = (ObjectNode) payload.deepCopy();
        
        jsonNode.remove(key);
        jsonNode.put(keyHolder , fieldName);
        jsonNode.set(fieldName, fieldValue);

        return new Message("", jsonNode);
    }
}
