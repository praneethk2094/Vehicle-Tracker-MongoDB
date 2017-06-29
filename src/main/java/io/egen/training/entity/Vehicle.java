package io.egen.training.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/*
* Vehicle class POJO
* Uses Lombok to generate GETTERS and SETTERS
* has composition to Alerts and VehicleReading
* can be used in case of RDBMS
* */
@Data
@Document
public class Vehicle {
    @Id
    @Field("vin")
    private String vin;
    private String make;
    private String model;
    private short year;
    private short redlineRpm;
    private float maxFuelVolume;
    private Date lastServiceDate;
    @Transient
    @JsonIgnore
    private List<VehicleReading> vehicleReadingList;
    @Transient
    @JsonIgnore
    private List<Alerts> alertsList;
}
