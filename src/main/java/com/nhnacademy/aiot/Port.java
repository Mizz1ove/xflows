package com.nhnacademy.aiot;

import java.util.LinkedList;

public class Port {

    private LinkedList<Wire> wireList;

    public Port(){
        this.wireList = new LinkedList<>();
    }

    public void out(Message message) {
        if (message != null) {
            for (Wire wire : wireList) {
                wire.put(message);
            }
        }
    }

    public Message get() {

        for (Wire wire : wireList) {
            if (wire.hasMessage()) {
                return wire.messageQueue.poll();
            }
        }
        return null;
    }

    public void bindWire(Wire wire) {
        wireList.add(wire);
    }

    public LinkedList<Wire> getWires() {
        return wireList;
    }

}
}
