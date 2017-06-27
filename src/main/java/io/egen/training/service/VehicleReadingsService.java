package io.egen.training.service;

import io.egen.training.entity.VehicleReading;

import java.util.List;

public interface VehicleReadingsService {
    List<VehicleReading> saveReadings(List<VehicleReading> vehicleReadingList);
    List<VehicleReading> findAllReadings();
    List<VehicleReading> findOneVehicleReadings(String vin);
    void deleteVehicleReading(VehicleReading vehicleReading);
}
