package io.egen.training.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
/*
* VehicleReading class POJO
* Uses Lombok to generate GETTERS and SETTERS
* indexes vin field for better to make queries quick
* */
@Data
@Document
public class VehicleReading {
    @Id
    private String vehicleReadingId;
    @Indexed
    private String vin;
    private double longitude;
    private double latitude;
    private Date timestamp;
    private double fuelVolume;
    private float speed;
    private short engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private short engineRpm;
    private Tires tires;
}
