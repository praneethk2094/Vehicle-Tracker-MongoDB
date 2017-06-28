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
/*
* VehicleReadingsServiceImpl implements from VehicleReadingsService
* */
@Service
public class VehicleReadingsServiceImpl implements VehicleReadingsService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleReadingRepository vehicleReadingRepository;
    @Autowired
    private AlertsService alertsService;
    /*
    * takes a list of vehicleReadings
    * throws bad request if any reading doesn't have VIN
    * creates alerts
    * saves the readings to database
    * */
    @Transactional
    public List<VehicleReading> saveReadings(final List<VehicleReading> vehicleReadingList) {
        if (vehicleReadingList.stream().filter(v -> (v.getVin() == null)).count() > 0) {
            throw new BadRequest("Vehicle readings must contain VIN");
        }
        for (VehicleReading vehicleReading :
                vehicleReadingList) {
            final Vehicle vehicle = vehicleRepository.findOne(vehicleReading.getVin());
            alertsService.createAlerts(vehicle, vehicleReading);
        }
        return vehicleReadingRepository.insert(vehicleReadingList);
    }
    /*
    * returns all vehicle readings in database
    * */
    @Transactional
    public List<VehicleReading> findAllReadings() {
        return vehicleReadingRepository.findAll();
    }
    /*
    * takes VIN
    * if readings exist for given vin returns reading
    * else throws BadRequest exception
    * */
    @Transactional
    public List<VehicleReading> findOneVehicleReadings(final String vin) {
        final List<VehicleReading> vehicleReading = vehicleReadingRepository.findAllByVin(vin);
        if(vehicleReading == null){
            throw new ResourceNotFound("Vehicle readings with "+vin+" vin doesn't exist");
        }
        return vehicleReading;
    }
    /*
    * takes VIN
    * if readings exist for given vin deletes all readings associated with the VIN
    * else throws BadRequest exception
    * */
    @Transactional
    public void deleteVehicleReadings(final String vin) {
        if(vehicleReadingRepository.findAllByVin(vin).isEmpty()){
            throw new BadRequest("No such vehicle reading found to delete");
        }
        vehicleReadingRepository.deleteAllByVin(vin);
    }
    /*
    * takes VehicleReading
    * if reading exist for given reading deletes reading
    * else throws BadRequest exception
    * */
    public void deleteOneVehicleReading(final VehicleReading vehicleReading){
        if(!vehicleReadingRepository.findAllByVin(vehicleReading.getVin()).contains(vehicleReading)){
            throw new BadRequest("No such vehicle reading found to delete");
        }
        vehicleReadingRepository.delete(vehicleReading);
    }
}
