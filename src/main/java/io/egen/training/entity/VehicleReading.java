package io.egen.training.entity;

import org.springframework.data.geo.Point;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by diksh on 6/23/2017.
 */
@Data
@Document
public class VehicleReading {
    private Vehicle vehicle;
    private Point location;
    private Date timeStamp;
    private double fuelVolume;
    private float speed;
    private short engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private short engineRpm;
    private Tires tires;
}

@Data
class Tires{
    private byte frontLeft;
    private byte frontRight;
    private byte rearLeft;
    private byte rearRight;
}
