package com.nhnacademy.aiot;

import java.util.LinkedList;
import java.util.Queue;

public class Port {

    private LinkedList<Wire> wireList;

    public Port(){
        this.wireList = new LinkedList<>();
    }

    public void out(Message message) {
        if(message != null){
            for(Wire wire : wireList){
                wire.push(message);
            }
        }
    }

    public void bindWire(Wire wire) {
        wireList.add(wire);    
    }
}
