package com.nhnacademy.aiot;

import java.util.LinkedList;
import java.util.Queue;

public class Wire {
    Queue<Message> messageQueue;

    public Wire() {
        messageQueue = new LinkedList<>();
    }

    public void put(Message message) {
        messageQueue.offer(message);
    }

    public boolean hasMessage() {
        return !messageQueue.isEmpty();
    }

    public Message get() {
        return messageQueue.poll();
    }

}
