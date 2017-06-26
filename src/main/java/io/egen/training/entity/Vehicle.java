package io.egen.training.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
public class Vehicle {
    @Id
    @Field("vin")
    private String vin;
    private String make;
    private String model;
    private short year;
    private short redLineRpm;
    private float maxFuelVolume;
    private Date lastServiceDate;
    private List<VehicleReading> vehicleReadings;
}
