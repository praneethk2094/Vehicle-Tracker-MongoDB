package io.egen.training.service;

import io.egen.training.aspect.BoundaryLogger;
import io.egen.training.exceptionHandling.BadRequest;
import io.egen.training.exceptionHandling.ResourceNotFound;
import io.egen.training.entity.Vehicle;
import io.egen.training.repository.AlertsRepository;
import io.egen.training.repository.VehicleReadingRepository;
import io.egen.training.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;
    private VehicleReadingRepository vehicleReadingRepository;
    private AlertsRepository alertsRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleReadingRepository vehicleReadingRepository, AlertsRepository alertsRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleReadingRepository = vehicleReadingRepository;
        this.alertsRepository = alertsRepository;
    }

    /*
        * takes list of vehicles
        * if any vehicle VIN is null throws BadRequest
        * else saves vehicle list
        * */
    @Transactional
    public List<Vehicle> saveVehicles(List<Vehicle> vehicleList) {


        if (vehicleList.stream().filter(v -> (v.getVin() == null)).count() > 0) {
            throw new BadRequest("Vehicles must contain VIN");
        }
        return vehicleRepository.save(vehicleList);
    }

    /*
    * find all vehicle in database
    * */
    @Transactional
    @BoundaryLogger
    public List<Vehicle> findAllVehicles() {
        String high = "HIGH";
        String medium = "MEDIUM";
        String low = "LOW";
        Date start = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.HOUR,-2);
        Date end = c.getTime();

        List<Vehicle> vehicleList = vehicleRepository.findAll();

        for(Vehicle vehicle: vehicleList){
            vehicle.setTwoHrAlert(alertsRepository.countAlertsByVinAndEngineRpmAlertAndTimestampBetween(vehicle.getVin(),high,end,start));
            vehicle.setHighAlert(alertsRepository.countAlertsByVinAndEngineRpmAlert(vehicle.getVin(),high));
            vehicle.setMediumAlert(alertsRepository.countAlertsByVinAndFuelVolumeAlert(vehicle.getVin(),medium));
            vehicle.setLowAlert(alertsRepository.countAlertsByVinAndTirePressureAlert(vehicle.getVin(),low));
        }


        return vehicleList;
    }

    /*
    * finds vehicle with VIN
    * if no such vehicle has that VIN throws ResourceNotFound exception
    * */
    @Transactional
    @BoundaryLogger
    public Vehicle findOneVehicle(String vin) {
        Vehicle vehicle = vehicleRepository.findOne(vin);
        String high = "HIGH";
        String medium = "MEDIUM";
        String low = "LOW";
        if (vehicle == null) {
            throw new ResourceNotFound("Vehicle cannot be found");
        }
        else{

            vehicle.setHighAlert(alertsRepository.countAlertsByVinAndEngineRpmAlert(vehicle.getVin(),high));
            vehicle.setMediumAlert(alertsRepository.countAlertsByVinAndFuelVolumeAlert(vehicle.getVin(),medium));
            vehicle.setLowAlert(alertsRepository.countAlertsByVinAndTirePressureAlert(vehicle.getVin(),low));

        }
        return vehicle;
    }

    /*
    * deletes vehicle by VIN
    * if no such vehicle has that VIN throws BadRequest exception
    * deletes all readings corresponding to that VIN
    * */
    @Transactional
    public void deleteVehicle(String vin) {
        Vehicle vehicle = vehicleRepository.findOne(vin);
        if (vehicle == null) {
            throw new BadRequest("No such vehicle vin found to delete");
        }
        vehicleRepository.delete(vehicle);
        vehicleReadingRepository.deleteAllByVin(vin);
        alertsRepository.deleteAllByVin(vin);
    }

    /*
    * deletes all vehicle, readings and alerts corresponding to that VIN
    * */
    @Transactional
    public void deleteAll() {
        vehicleRepository.deleteAll();
        vehicleReadingRepository.deleteAll();
        alertsRepository.deleteAll();
    }
}
