package io.egen.training.service;

import io.egen.training.entity.VehicleReading;

import java.util.List;

/*
* service interface with saveReadings, findAllReadings, findOneVehicleReadings, deleteVehicleReadings, deleteOneVehicleReading
* */
public interface VehicleReadingsService {
    List<VehicleReading> saveReadings(List<VehicleReading> vehicleReadingList);
    List<VehicleReading> findAllReadings();
    List<VehicleReading> findOneVehicleReadings(String vin);
    void deleteVehicleReadings(String vin);
    void deleteOneVehicleReading(VehicleReading vehicleReading);
    void deleteAll();
}
