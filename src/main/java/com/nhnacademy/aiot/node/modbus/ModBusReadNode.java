package com.nhnacademy.aiot.node.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.node.Node;
import com.nhnacademy.aiot.utils.ByteUtils;
import com.nhnacademy.aiot.utils.JSONUtils;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ModBusReadNode extends Node{

    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private int transactionId = 0;
    private int address;

    public ModBusReadNode(String nodeId, boolean hasInputPort, int outputPortCount, String clientId, int address) {
        super(nodeId, hasInputPort, outputPortCount);
        try {
            socket = ModbusClients.clientMap.get(clientId);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();  
            this.address = address;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process() {
        try {  
            outputStream.write(new byte[]{0, (byte)transactionId++, 0, 0, 0, 6, 1, 3, 0, (byte)address, 0, 1});
            outputStream.flush();
            byte[] inputBuffer = new byte[1024];
            int receivedLength = inputStream.read(inputBuffer, 0, inputBuffer.length);
            
            int unitId = inputBuffer[6];
            int functionCode = inputBuffer[7];
            int value = ByteUtils.concatBytes(inputBuffer[9],inputBuffer[10]);

            ObjectNode jsonObject = JSONUtils.getMapper().createObjectNode();
            ObjectNode payload= jsonObject.deepCopy();
            payload.put("unitId", unitId);
            payload.put("functionCode", functionCode);
            payload.put("address", address);
            payload.put("data", value);
            Message msg = new Message("", payload);
            out(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("error {}" , e.getMessage());
        }
    }    
    

}
