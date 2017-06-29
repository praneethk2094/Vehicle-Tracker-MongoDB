package io.egen.training.service;

import io.egen.training.ExceptionHandling.BadRequest;
import io.egen.training.ExceptionHandling.ResourceNotFound;
import io.egen.training.entity.Vehicle;
import io.egen.training.repository.AlertsRepository;
import io.egen.training.repository.VehicleReadingRepository;
import io.egen.training.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleReadingRepository vehicleReadingRepository;
    @Autowired
    private AlertsRepository alertsRepository;

    /*
    * takes list of vehicles
    * if any vehicle VIN is null throws BadRequest
    * else saves vehicle list
    * */
    @Transactional
    public void saveVehicles(List<Vehicle> vehicleList) {
        if (vehicleList.stream().filter(v -> (v.getVin() == null)).count() > 0) {
            throw new BadRequest("Vehicles must contain VIN");
        }
        vehicleRepository.save(vehicleList);
    }

    /*
    * find all vehicle in database
    * */
    @Transactional
    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    /*
    * finds vehicle with VIN
    * if no such vehicle has that VIN throws ResourceNotFound exception
    * */
    @Transactional
    public Vehicle findOneVehicle(String vin) {
        Vehicle vehicle = vehicleRepository.findOne(vin);
        if (vehicle == null) {
            throw new ResourceNotFound("Vehicle cannot be found");
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
    public void deleteAll(){
        vehicleRepository.deleteAll();
        vehicleReadingRepository.deleteAll();
        alertsRepository.deleteAll();
    }
}
