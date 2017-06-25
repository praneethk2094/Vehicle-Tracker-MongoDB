package io.egen.training.service;

import io.egen.training.entity.Vehicle;
import io.egen.training.entity.VehicleReading;
import io.egen.training.repository.VehicleReadingRepository;
import io.egen.training.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    VehicleReadingRepository vehicleReadingRepository;

    public Vehicle save(Vehicle vehicle){
        Vehicle vehicle1 = vehicleRepository.save(vehicle);
        return vehicle1;
    }

    public List<Vehicle> saveVehicles(List<Vehicle> vehicleList) {
        List<Vehicle> vehicleList1 = vehicleRepository.save(vehicleList);
        return vehicleList1;
    }

    public List<Vehicle> findAllVehicles() {
        List<Vehicle> vehicleList1 = vehicleRepository.findAll();
        return vehicleList1;
    }

    public Vehicle findOneVehicle(String vin) {
        Vehicle vehicle = vehicleRepository.findOne(vin);
        return vehicle;
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        Vehicle vehicle1 = vehicleRepository.save(vehicle);
        return vehicle1;
    }

    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }

    public List<VehicleReading> saveReadings(List<VehicleReading> vehicleReadingList) {
        return null;
    }

    public List<VehicleReading> findAllReadings() {
        return null;
    }

    public VehicleReading findOneReading(String vin) {
        return null;
    }

    public VehicleReading updateVehicleReading(VehicleReading vehicleReading) {
        return null;
    }

    public void deleteVehicleReading(VehicleReading vehicleReading) {

    }
}
