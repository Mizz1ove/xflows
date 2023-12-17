package com.nhnacademy.aiot.node;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhnacademy.aiot.utils.JSONUtils;
//import com.nhnacademy.aiot.modbus.FunctionCodes;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModbusServer extends Node {

    private int[] holdingRegister;
    private int[] inputRegister;
    private final int HOLDING_REGISTER_SIZE = 1000;
    private final int INPUT_REGISTER_SIZE = 1000;
    private final int PORT_NUM = 11502;
    private final int THREADPOOL_SIZE = 10;

    private final int INPUT_BUFFER_SIZE = 1024;
    private final int MBAP_HAEDER_LENGTH = 7;
    private static final int FUNCTION_CODE_IDX = 7;
    private static final int LENGTH_IDX = 5;
    private static final int UNIT_ID_IDX = 6;
    private static final byte UNIT_ID = 0;

    ServerSocket serverSocket;
    ExecutorService executorService;
    ModbusServer(String id){
        super(id, true, 0);
        this.holdingRegister = new int[HOLDING_REGISTER_SIZE];
        this.inputRegister = new int[INPUT_REGISTER_SIZE];

        this.executorService = Executors.newFixedThreadPool(THREADPOOL_SIZE);
    }

    @Override
    public void preprocess() {
        try {
            serverSocket = new ServerSocket(PORT_NUM);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void process() {
        try {
            Socket socket = serverSocket.accept();
            //executorService.execute(()->뻥션(socket));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void postprocess() {

    }

    // private void 뻥션(Socket socket){
    //     try(BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
    //         BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream())
    //     ){
    //         while(socket.isConnected()) {
    //             byte[] inputBuffer = new byte[INPUT_BUFFER_SIZE];
    //             int receicedLength = bufferedInputStream.read(inputBuffer, 0, inputBuffer.length);
    //             byte[] mbapHeader = Arrays.copyOfRange(inputBuffer, 0, MBAP_HAEDER_LENGTH);
    //             if (!isValidPacket(receicedLength, inputBuffer)) break;

    //             byte[] requestPdu = Arrays.copyOfRange(inputBuffer, 7, receicedLength);
    //             //FunctionCodes function = FunctionCodes.getByCode(inputBuffer[FUNCTION_CODE_IDX]);

    //             byte[] responsePdu = function.getFunction().apply(requestPdu);

    //             byte[] response = new byte[MBAP_HAEDER_LENGTH + responsePdu.length];

    //             System.arraycopy(responsePdu, 0, response, 0, MBAP_HAEDER_LENGTH);
    //             System.arraycopy(responsePdu, 0, response, 7, responsePdu.length);

    //             response[LENGTH_IDX] = (byte) (responsePdu.length + 1);

    //             bufferedOutputStream.write(response);
    //             bufferedOutputStream.flush();

    //             // TODO 이거 왜 함?
    //             ObjectNode jsonNode = JSONUtils.getMapper().createObjectNode();
    //             jsonNode.put("ModbusRespose", Arrays.toString(response));
    //             Msg msg = new Msg("", jsonNode);
    //             out(msg);

    //         }

    //     } catch (IOException e){
    //         // TODO log 찍기
    //     }
    // }

    // TODO 예외처리
    private boolean isValidPacket(int receivedLength, byte[] inputBuffer){
        if(receivedLength < 0 || UNIT_ID != inputBuffer[UNIT_ID_IDX]) return false;
        if(!(receivedLength>7) && !(6+inputBuffer[LENGTH_IDX] == receivedLength)) return false;

        return true;
    }


}
