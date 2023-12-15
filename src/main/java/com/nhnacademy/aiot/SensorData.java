package com.nhnacademy.aiot;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SensorData {
    private String deviceId;
    private String site;
    private String branch;
    private String place;
    private String unitId;
    private String register;
    private int address;
    private double value;

}
