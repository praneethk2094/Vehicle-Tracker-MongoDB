package io.egen.training.service;


import io.egen.training.entity.Alerts;
import io.egen.training.entity.Vehicle;
import io.egen.training.entity.VehicleReading;
import io.egen.training.repository.AlertsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
* implements AlertsService and its methods
* does business logic for Alerts
* */
@Service
public class AlertsServiceImpl implements AlertsService {

    private AlertsRepository alertsRepository;
    @Autowired
    public AlertsServiceImpl(AlertsRepository alertsRepository) {
        this.alertsRepository = alertsRepository;
    }

    @Transactional
    public List<Alerts> findAll() {
        return alertsRepository.findAll();
    }

    @Transactional
    public List<Alerts> findAllByVin(String vin) {
        return alertsRepository.findAll();
    }

    @Transactional
    public void deleteAllAlertsByVehicleReadingId(String vrId){

        alertsRepository.delete(vrId);
    }
    @Transactional
    public void deleteAll(){
        alertsRepository.deleteAll();
    }
    /*
    * takes vehicle and vehicle reading
    * and checks for conditions
    * creates alerts if any
    * stores in database
    * */
    @Transactional
    public Alerts createAlerts(final Vehicle vehicle, final VehicleReading vehicleReading) {
        Alerts alerts = new Alerts();
        final List<Byte> tires = vehicleReading.getTires().getTirePressures();
        if (vehicleReading.getEngineRpm() >= vehicle.getRedlineRpm()) {
            alerts.setVin(vehicle.getVin());
            alerts.setEngineRpmAlert(Alerts.Alert.HIGH);
        }
        if (vehicleReading.getFuelVolume() < (vehicle.getMaxFuelVolume() % 10)) {
            alerts.setVin(vehicle.getVin());
            alerts.setFuelVolumeAlert(Alerts.Alert.MEDIUM);
        }
        if (tires.stream().filter(i -> (i > 36 || i < 32)).count() > 0) {
            alerts.setVin(vehicle.getVin());
            alerts.setTirePressureAlert(Alerts.Alert.LOW);
        }
        if (vehicleReading.isCheckEngineLightOn()) {
            alerts.setVin(vehicle.getVin());
            alerts.setCheckEngineLightOnAlert(Alerts.Alert.LOW);
        }
        if (vehicleReading.isEngineCoolantLow()) {
            alerts.setVin(vehicle.getVin());
            alerts.setEngineCoolantAlert(Alerts.Alert.LOW);
        }
        if(alerts.getVin()!=null){
            alerts.setAlertId(vehicleReading.getVehicleReadingId());
            return alertsRepository.insert(alerts);
        }
        return null;
    }

}
