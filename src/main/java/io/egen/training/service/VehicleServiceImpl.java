package io.egen.training.service;

import io.egen.training.entity.Alerts;
import io.egen.training.entity.Vehicle;
import io.egen.training.entity.VehicleReading;
import io.egen.training.repository.AlertsRepository;
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
    @Autowired
    AlertsRepository alertsRepository;

    public Vehicle save(Vehicle vehicle){
        if(vehicle==null){
            //error
        }
        Vehicle vehicle1 = vehicleRepository.save(vehicle);
        return vehicle1;
    }

    public List<Vehicle> saveVehicles(List<Vehicle> vehicleList) {
        if(vehicleList.isEmpty()){
            //error
        }
        List<Vehicle> vehicleList1 = vehicleRepository.save(vehicleList);
        return vehicleList1;
    }

    public List<Vehicle> findAllVehicles() {
        List<Vehicle> vehicleList1 = vehicleRepository.findAll();
        return vehicleList1;
    }

    public Vehicle findOneVehicle(String vin) {
        Vehicle vehicle = vehicleRepository.findOne(vin);
        if(vehicle == null){
            //error
        }
        return vehicle;
    }

    public void deleteVehicle(Vehicle vehicle) {
        if(vehicle == null){
            //error
        }
        vehicleRepository.delete(vehicle);
    }

    /*
    * Vehicle Readings service implementation
    * */
    public List<VehicleReading> saveReadings(List<VehicleReading> vehicleReadingList) {
        for (VehicleReading vehicleReading:
             vehicleReadingList) {
            Vehicle vehicle = vehicleRepository.findOne(vehicleReading.getVin());
            createAlerts(vehicle, vehicleReading);
        }
        if(vehicleReadingList.isEmpty()){
            // throw error
        }
        List<VehicleReading> vehicleReadingList1 = vehicleReadingRepository.save(vehicleReadingList);
        return vehicleReadingList1;
    }

    private void createAlerts(Vehicle vehicle, VehicleReading vehicleReading) {
        Alerts alerts = new Alerts();
        if(vehicleReading.getEngineRpm() >= vehicle.getRedLineRpm()){
            alerts.setVin(vehicle.getVin());
            alerts.setFuelVolumeAlert(Alerts.Alert.HIGH);
        }
        if(vehicleReading.getFuelVolume() < (vehicle.getMaxFuelVolume() % 10)){
            alerts.setVin(vehicle.getVin());
            alerts.setFuelVolumeAlert(Alerts.Alert.MEDIUM);
        }
    }

    public List<VehicleReading> findAllReadings() {
        return vehicleReadingRepository.findAll();
    }

    public VehicleReading findOneReading(String vin) {
        VehicleReading vehicleReading = vehicleReadingRepository.findOne(vin);
        if(vehicleReading==null){
            //error
        }
        return vehicleReading;
    }

    public void deleteVehicleReading(VehicleReading vehicleReading) {
        if(findOneReading(vehicleReading.getVin())==null){
            //error
        }
        vehicleReadingRepository.delete(vehicleReading);
    }
}
