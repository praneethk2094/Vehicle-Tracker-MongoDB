package io.egen.training.service;

import io.egen.training.ExceptionHandling.BadRequest;
import io.egen.training.ExceptionHandling.ResourceNotFound;
import io.egen.training.entity.Alerts;
import io.egen.training.entity.Vehicle;
import io.egen.training.entity.VehicleReading;
import io.egen.training.repository.AlertsRepository;
import io.egen.training.repository.VehicleReadingRepository;
import io.egen.training.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/*
*
*
* @transactional
*log4j
* */
import java.util.List;
@Service
public class VehicleReadingsServiceImpl implements VehicleReadingsService {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    VehicleReadingRepository vehicleReadingRepository;
    @Autowired
    AlertsRepository alertsRepository;

    @Transactional
    public List<VehicleReading> saveReadings(List<VehicleReading> vehicleReadingList) {
        for (VehicleReading vehicleReading:
                vehicleReadingList) {
            Vehicle vehicle = vehicleRepository.findOne(vehicleReading.getVin());
            createAlerts(vehicle, vehicleReading);
        }
        if(vehicleReadingList.stream().filter(v -> (v.getVin()==null)).count() > 0){
            throw new BadRequest("Vehicle readings must contain VIN");
        }
        List<VehicleReading> vehicleReadingList1 = vehicleReadingRepository.insert(vehicleReadingList);
        return vehicleReadingList1;
    }

    @Transactional
    public List<VehicleReading> findAllReadings() {
        return vehicleReadingRepository.findAll();
    }

    @Transactional
    public List<VehicleReading> findOneVehicleReadings(String vin) {
        List<VehicleReading> vehicleReading = vehicleReadingRepository.findAllByVin(vin);
        if(vehicleReading == null){
            throw new ResourceNotFound("Vehicle readings with "+vin+" vin doesn't exist");
        }
        return vehicleReading;
    }

    @Transactional
    public void deleteVehicleReading(VehicleReading vehicleReading) {
        if(findOneVehicleReadings(vehicleReading.getVin())== null){
            throw new BadRequest("No such vehicle reading found to delete");
        }
        vehicleReadingRepository.delete(vehicleReading);
    }


    @Transactional
    private void createAlerts(Vehicle vehicle, VehicleReading vehicleReading) {
        Alerts alerts = new Alerts();
        List<Byte> tires = vehicleReading.getTires().getTirePressures();
        if(vehicleReading.getEngineRpm() >= vehicle.getRedLineRpm()){
            alerts.setVin(vehicle.getVin());
            alerts.setEngineRpmAlert(Alerts.Alert.HIGH);
        }
        if(vehicleReading.getFuelVolume() < (vehicle.getMaxFuelVolume() % 10)){
            alerts.setVin(vehicle.getVin());
            alerts.setFuelVolumeAlert(Alerts.Alert.MEDIUM);
        }
        if(tires.stream().filter(i->(i>36 || i<32)).count() > 0){
            alerts.setVin(vehicle.getVin());
            alerts.setTirePressureAlert(Alerts.Alert.LOW);
        }
        if(vehicleReading.isCheckEngineLightOn()){
            alerts.setVin(vehicle.getVin());
            alerts.setCheckEngineLightOnAlert(Alerts.Alert.LOW);
        }
        if(vehicleReading.isEngineCoolantLow()){
            alerts.setVin(vehicle.getVin());
            alerts.setEngineCoolantAlert(Alerts.Alert.LOW);
        }
        alertsRepository.save(alerts);
    }

}
