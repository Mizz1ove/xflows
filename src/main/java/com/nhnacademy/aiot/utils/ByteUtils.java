package com.nhnacademy.aiot.utils;

public class ByteUtils {

    private ByteUtils(){

    }

    public static int concatBytes(byte firstByte, byte secondByte) {
        return ((firstByte & 0xFF) << 8) | (secondByte & 0xFF);
    }

    public static byte[] splitIntToBytes(int value){
        byte first = (byte) ((value >> 8) & 0xFF);
        byte second = (byte) (value & 0xFF);

        return new byte[] {first, second};
    }

}
