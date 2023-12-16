package com.nhnacademy.aiot;

import java.util.LinkedList;
import java.util.Queue;

public class Wire {
    Queue<Message> msgQueue;
    private int msgCount;
    private int outmsgCount;

    public Wire(){
        msgQueue = new LinkedList<>();
        this.msgCount = 0;
        this.outmsgCount = 0;
    }

    public void put(Message message) {
        msgQueue.offer(message);
        msgCount++;
    }

    public boolean hasMessage() {
        return !msgQueue.isEmpty();
    }

    public Message get() {
        outmsgCount++;
        return msgQueue.poll();
    }

    public int getMsgCount() {
        return msgCount;
    }

    public int getOutMsgCount(){
        return outmsgCount;
    }

}
