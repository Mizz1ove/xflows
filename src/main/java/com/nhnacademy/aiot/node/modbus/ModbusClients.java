package com.nhnacademy.aiot.node.modbus;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ModbusClients {
    public static Map<String, Socket> clientMap = new HashMap<>();

    static {
        try {
            clientMap.put("node-red1", new Socket("172.19.0.11", 1885));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
