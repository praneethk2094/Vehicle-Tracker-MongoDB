package io.egen.training.service;

import io.egen.training.entity.Vehicle;
import io.egen.training.entity.VehicleReading;

import java.util.List;

public interface VehicleService {

    Vehicle save(Vehicle vehicle);
    List<Vehicle> saveVehicles(List<Vehicle> vehicleList);
    List<Vehicle> findAllVehicles();
    Vehicle findOneVehicle(String vin);
    void deleteVehicle(Vehicle vehicle);

    List<VehicleReading> saveReadings(List<VehicleReading> vehicleReadingList);
    List<VehicleReading> findAllReadings();
    VehicleReading findOneReading(String vin);
    void deleteVehicleReading(VehicleReading vehicleReading);
}
