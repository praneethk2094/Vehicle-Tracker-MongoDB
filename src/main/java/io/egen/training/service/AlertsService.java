package io.egen.training.service;


import io.egen.training.entity.Alerts;
import io.egen.training.entity.Vehicle;
import io.egen.training.entity.VehicleReading;

import java.util.List;

/*
* AlertsService interface providing createAlerts method to implement
* */
public interface AlertsService {
    Alerts createAlerts(Vehicle vehicle, VehicleReading vehicleReading);

    List<Alerts> findAll();

    List<Alerts> findAllByVin(String vin);

    void deleteAllAlertsByVehicleReadingId(String vrid);
    void deleteAll();
}
