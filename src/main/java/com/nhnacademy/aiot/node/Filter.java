package com.nhnacademy.aiot.node;

public class Filter extends Node {
    protected Filter(String id, boolean hasInputPort, int outputPortCount) {
        super(id, hasInputPort, outputPortCount);
        //TODO Auto-generated constructor stub
    }

    String id;
    int outputPortCount;

    @Override
    protected void preprocess() {
    }

    @Override
    protected void process() {
    }

    @Override
    protected void postprocess() {
    }
    
}
