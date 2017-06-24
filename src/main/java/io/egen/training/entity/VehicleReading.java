package io.egen.training.entity;

import org.springframework.data.geo.Point;

import java.util.Date;

public class VehicleReading {
    private Vehicle vehicle;
    private Point location;
    private Date timeStamp;
    private double fuelVolume;
    private float speed;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(double fuelVolume) {
        this.fuelVolume = fuelVolume;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public short getEngineHp() {
        return engineHp;
    }

    public void setEngineHp(short engineHp) {
        this.engineHp = engineHp;
    }

    public boolean isCheckEngineLightOn() {
        return checkEngineLightOn;
    }

    public void setCheckEngineLightOn(boolean checkEngineLightOn) {
        this.checkEngineLightOn = checkEngineLightOn;
    }

    public boolean isEngineCoolantLow() {
        return engineCoolantLow;
    }

    public void setEngineCoolantLow(boolean engineCoolantLow) {
        this.engineCoolantLow = engineCoolantLow;
    }

    public boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    public void setCruiseControlOn(boolean cruiseControlOn) {
        this.cruiseControlOn = cruiseControlOn;
    }

    public short getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(short engineRpm) {
        this.engineRpm = engineRpm;
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    private short engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private short engineRpm;
    private Tires tires;
}

class Tires{
    private byte frontLeft;
    private byte frontRight;

    public byte getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(byte frontLeft) {
        this.frontLeft = frontLeft;
    }

    public byte getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(byte frontRight) {
        this.frontRight = frontRight;
    }

    public byte getRearLeft() {
        return rearLeft;
    }

    public void setRearLeft(byte rearLeft) {
        this.rearLeft = rearLeft;
    }

    public byte getRearRight() {
        return rearRight;
    }

    public void setRearRight(byte rearRight) {
        this.rearRight = rearRight;
    }

    private byte rearLeft;
    private byte rearRight;
}
