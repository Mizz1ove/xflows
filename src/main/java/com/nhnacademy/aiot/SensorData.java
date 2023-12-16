package com.nhnacademy.aiot;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SensorData {
    private String deviceId;
    private String site;
    private String branch;
    private String place;
    private String unitId;
    private String register;
    private int address;
    private double value;
    private double ratio;

    @Override
    public String toString() {
        return "SensorData [deviceId=" + deviceId + ", site=" + site + ", branch=" + branch
                + ", place=" + place + ", unitId=" + unitId + ", register=" + register
                + ", address=" + address + ", value=" + value + "]";
    }

}
