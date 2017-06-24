package io.egen.training.entity;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Data
public class Vehicle {
    private String vin;
    private String make;
    private String model;
    private short year;
    private short redLineRpm;
    private float maxFuelVolume;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public short getRedLineRpm() {
        return redLineRpm;
    }

    public void setRedLineRpm(short redLineRpm) {
        this.redLineRpm = redLineRpm;
    }

    public float getMaxFuelVolume() {
        return maxFuelVolume;
    }

    public void setMaxFuelVolume(float maxFuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public List<VehicleReading> getVehicleReadings() {
        return vehicleReadings;
    }

    public void setVehicleReadings(List<VehicleReading> vehicleReadings) {
        this.vehicleReadings = vehicleReadings;
    }

    private Date lastServiceDate;
    private List<VehicleReading> vehicleReadings;
    
}
