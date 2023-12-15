package com.nhnacademy.aiot;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Wire {
    Queue<Message> messageQueue;
    
    public Wire(){
        messageQueue = new LinkedBlockingDeque<>();
    }

    public Message get() {
        return messageQueue.poll();
    }

    public void push(Message msg) {
        messageQueue.add(msg);
    }

}
