package com.nhnacademy.aiot;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Port {
    private List<Wire> wires;
    private Queue<Message> messageQueue;

    public Port() {
        this.wires = new ArrayList<>();
        this.messageQueue = new ConcurrentLinkedQueue<>();
    }

    public void addWire(Wire wire) {
        wires.add(wire);
    }

    public void removeWire(Wire wire) {
        wires.remove(wire);
    }

    public List<Wire> getWires() {
        return wires;
    }

    public void out(Message outMessage) {
        if (outMessage != null) {
            for (Wire wire : wires) {
                wire.put(outMessage);
            }
        }
    }

    public boolean hasMessage(){

        if(messageQueue.isEmpty()){
            collectMsgFromWire();
            return false;
        }
        return true;
    }

    /**
     * 연결된 여러 wire 에서 메시지를 하나씩 가져와서 Port의 메시지 큐에 추가하여. wire로부터 오는 메시지를 큐에 모아주는 메서드.
     */
    private void collectMsgFromWire(){
        for (Wire wire : wires) {
            if (wire.hasMessage()) {
                messageQueue.add(wire.get());
            }
        }
    }

    public Message getMsg() {
        return messageQueue.poll();
    }
}

