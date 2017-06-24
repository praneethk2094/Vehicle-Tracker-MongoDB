package io.egen.training.entity;


public class Alerts {
    private enum Alert{
        HIGH, MEDIUM, LOW
    }

    private Alert engineRpmAlert;
    private Alert fuelVolumeAlert;
    private Alert tirePressureAlert;
    private Alert engineCoolantAlert;
    private Alert checkEngineLightOnAlert;
   
}
