package com.nhnacademy.aiot.node;

import com.nhnacademy.aiot.Message;
import com.nhnacademy.aiot.Port;
import com.nhnacademy.aiot.Wire;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;

abstract class Node implements Runnable {

    Thread thread;
    private final String id;
    protected Port inputPort;
    protected Port[] outputPorts;
    protected static int nodeCount;

    protected Node(String id, boolean hasInputPort, int outputPortCount) {
        this.thread = new Thread();
        this.id = id;
        this.outputPorts = new Port[outputPortCount];

        for (int i = 0; i < outputPortCount; i++) {

            outputPorts[i] = new Port();
        }

        if (hasInputPort) inputPort = new Port();

    }

    protected Node(boolean hasInputPort, int outputPortCount) {

        this(UUID.randomUUID().toString(), hasInputPort, outputPortCount);
    }

    protected abstract void preprocess();
    protected abstract void process();
    protected abstract void postprocess();

    public void out(Message message) {

        outputPorts[0].out(message);
    }

    public void out(Message... messages) {

        for (int i = 0; i < messages.length; i++) {
            outputPorts[i].out(messages[i]);
        }

    }

    public void bindInputWire(Wire wire) {

        inputPort.bindWire(wire);
    }

    public void bindOutputWire(Wire wire, int portIndex) {

        outputPorts[portIndex].bindWire(wire);
    }

    @Override
    public void run() {
        try {
            preprocess();
            while ((thread != null) &&!Thread.currentThread().isInterrupted()) {

                process();
                Thread.sleep(1000);

            }

            postprocess();

        } catch (InterruptedException e) {

            if (thread != null) thread.interrupt();
            throw new RuntimeException(e);
        }

    }

    public void start() {
        thread.start();
    }




}

