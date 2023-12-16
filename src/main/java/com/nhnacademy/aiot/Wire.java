package com.nhnacademy.aiot;

import java.util.LinkedList;
import java.util.Queue;

public class Wire {
    Queue<Message> msgQueue;

    public Wire(){
        msgQueue = new LinkedList<>();
    }

    public void put(Message message) {
        msgQueue.offer(message);
    }

    public boolean hasMessage() {
        return !msgQueue.isEmpty();
    }

    public Message get() {
        return msgQueue.poll();
    }

}
