package com.nhnacademy.aiot.node;

import com.fasterxml.jackson.databind.JsonNode;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.Port;
import com.nhnacademy.aiot.Wire;
import com.nhnacademy.aiot.utils.JSONUtils;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MqttMessagePreprocessing extends Node{

    protected MqttMessagePreprocessing(String id, boolean hasInputPort, int outputPortCount) {
        super(id, hasInputPort, outputPortCount);
    }

    @Override
    protected void process() {
        //Message message = inputPort.get();

        //JsonNode msg = message.PhasePath("payload._msgid", message.toString());
//        String msgid = jsonUtils.convertToString(msg);

//        String msgid = message.PhasePath("payload._msgid", message.toString());
//        String site = message.PhasePath("payload.deviceInfo.tags.site",message.toString());
//        String branch = message.PhasePath("payload.deviceInfo.tags.branch", message.toString());
//        String place = message.PhasePath("payload.deviceInfo.tags.place", message.toString());
    }


}
