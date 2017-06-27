package io.egen.training.service;

import io.egen.training.ExceptionHandling.BadRequest;
import io.egen.training.ExceptionHandling.ResourceNotFound;
import io.egen.training.entity.Vehicle;
import io.egen.training.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Transactional
    public Vehicle save(Vehicle vehicle){
        if(vehicle.getVin() == null){
            throw new BadRequest("Vehicle VIN cannot be null");
        }
        Vehicle vehicle1 = vehicleRepository.save(vehicle);
        return vehicle1;
    }

    @Transactional
    public List<Vehicle> saveVehicles(List<Vehicle> vehicleList) {
        if(vehicleList.stream().filter(v -> (v.getVin()==null)).count() > 0){
            throw new BadRequest("Vehicles must contain VIN");
        }
        List<Vehicle> vehicleList1 = vehicleRepository.save(vehicleList);
        return vehicleList1;
    }

    @Transactional
    public List<Vehicle> findAllVehicles() {
        List<Vehicle> vehicleList1 = vehicleRepository.findAll();
        return vehicleList1;
    }

    @Transactional
    public Vehicle findOneVehicle(String vin) {
        Vehicle vehicle = vehicleRepository.findOne(vin);
        if(vehicle == null){
            throw new ResourceNotFound("Vehicle cannot be found");
        }
        return vehicle;
    }

    @Transactional
    public void deleteVehicle(Vehicle vehicle) {
        if(vehicle == null){
            throw new BadRequest("No such vehicle found to delete");
        }
        vehicleRepository.delete(vehicle);
    }
}
