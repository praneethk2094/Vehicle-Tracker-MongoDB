package io.egen.training.service;

import io.egen.training.exceptionHandling.BadRequest;
import io.egen.training.exceptionHandling.ResourceNotFound;
import io.egen.training.entity.Vehicle;
import io.egen.training.entity.VehicleReading;
import io.egen.training.repository.VehicleReadingRepository;
import io.egen.training.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
* VehicleReadingsServiceImpl implements from VehicleReadingsService
* */
@Service
public class VehicleReadingsServiceImpl implements VehicleReadingsService {

    private VehicleRepository vehicleRepository;
    private VehicleReadingRepository vehicleReadingRepository;
    private AlertsService alertsService;

    @Autowired
    public VehicleReadingsServiceImpl(VehicleRepository vehicleRepository, VehicleReadingRepository vehicleReadingRepository, AlertsService alertsService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleReadingRepository = vehicleReadingRepository;
        this.alertsService = alertsService;
    }

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
            if (vehicle == null)
                throw new BadRequest("No associated vehicle found for given Reading's VIN: " + vehicleReading.getVin());
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
        if (vehicleReading == null) {
            throw new ResourceNotFound("Vehicle readings with " + vin + " vin doesn't exist");
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
        if (vehicleReadingRepository.findAllByVin(vin).isEmpty()) {
            throw new BadRequest("No such vehicle reading found to delete");
        }
        vehicleReadingRepository.deleteAllByVin(vin);
        List<VehicleReading> vehicleReadingList = vehicleReadingRepository.findAllByVin(vin);
        vehicleReadingList.forEach(v -> alertsService
                .deleteAllAlertsByVehicleReadingId(v.getVehicleReadingId()));
    }

    /*
    * takes VehicleReading
    * if reading exist for given reading deletes reading
    * else throws BadRequest exception
    * */
    @Transactional
    public void deleteOneVehicleReading(final VehicleReading vehicleReading) {
        if (!vehicleReadingRepository.findAllByVin(vehicleReading.getVin()).contains(vehicleReading)) {
            throw new BadRequest("No such vehicle reading found to delete");
        }
        alertsService.deleteAllAlertsByVehicleReadingId(vehicleReading.getVehicleReadingId());
        vehicleReadingRepository.delete(vehicleReading);
    }

    /*
    * delete all readings and alerts
    * */
    @Transactional
    public void deleteAll() {
        alertsService.deleteAll();
        vehicleReadingRepository.deleteAll();
    }
}
