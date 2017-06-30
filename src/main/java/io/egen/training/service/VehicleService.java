package io.egen.training.service;

import io.egen.training.entity.Vehicle;

import java.util.List;

/*
* VehicleService interface providing methods to save, find, delete vehicles
* */
public interface VehicleService {

    List<Vehicle> saveVehicles(List<Vehicle> vehicleList);

    List<Vehicle> findAllVehicles();

    Vehicle findOneVehicle(String vin);

    void deleteVehicle(String vin);

    void deleteAll();
}
