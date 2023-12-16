package com.nhnacademy.aiot.node;

import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.Port;
import com.nhnacademy.aiot.Wire;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Node implements Runnable {

    private Thread thread;
    protected Port inputPort;
    protected Port[] outputPorts;
    protected static int nodeCount;
    protected String name;

    protected int receivedMessageCount;
    protected int sendMessageCount;

    protected final String id;


    protected Node(String id, boolean hasInputPort, int outputPortCount) {
        this.id = id;
        this.receivedMessageCount = 0;
        this.sendMessageCount = 0;

        if (hasInputPort) {
            this.inputPort = new Port();
        }

        this.outputPorts = new Port[outputPortCount];
        name = getClass().getSimpleName() + "_" + nodeCount++;
        log.info("create node : " + name);

        for (int i = 0; i < outputPortCount; i++) {
            outputPorts[i] = new Port();
        }
    }

    protected Node(String id, int outputPortCount) {
        this(id, true, outputPortCount);
    }

    protected void preprocess() {
        log.info("start node : " + name);
    }

    protected void process() {
        // 이 메서드는 상속받는 하위 클래스에서 구현한다.
    }

    protected void postprocess() {
        log.info(this.getClass().getSimpleName() + " - stop");
    }

    protected void out(Message outMessage) {

        outputPorts[0].out(outMessage);
    }

    protected void out(Message... outMessages) {

        for (int i = 0; i < outMessages.length; i++) {
            outputPorts[i].out(outMessages[i]);
        }
    }

    public void setInputWire(Wire inputWire) {
        inputPort.addWire(inputWire);
    }

    public void setOutputWire(int portIdx, Wire outputWire) {
        if (portIdx < outputPorts.length) {
            outputPorts[portIdx].addWire(outputWire);
        }
    }

    public boolean MsgReceivedCount() {
        // 메시지 수신 횟수 세는 로직
        if (inputPort.hasMessage()) {
            receivedMessageCount++;
            return true;
        }
        else {
            return false;
        }
    }

    public int getMsgReceivedCount(){
        // 메시지 수신 횟수 반환
        return receivedMessageCount;
    }

    public int getMsgSendCount(){
        //메시지 송신 횟수 반환
        return sendMessageCount;
    }

    public int getMsgErrorCount(){
        //에러 횟수 반환
        return receivedMessageCount - sendMessageCount;
    }


    public synchronized void start() {
        thread = new Thread(this, this.getClass().getSimpleName());
        thread.start();
    }

    @Override
    public void run() {
        preprocess();

        while ((thread != null) && thread.isAlive()) {
            process();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                if (thread != null) {
                    thread.interrupt();
                }
            }
        }

        postprocess();
    }
}
