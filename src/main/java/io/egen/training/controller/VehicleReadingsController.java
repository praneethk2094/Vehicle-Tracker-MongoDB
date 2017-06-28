package io.egen.training.controller;

import io.egen.training.entity.VehicleReading;
import io.egen.training.service.VehicleReadingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/*
*VehicleReadingController is a rest controller which maps requests to the request mapping provided and delegates
* work to VehicleService service layer to deal with vehicle readings
*
* updateVehicleReadings method adds list of vehicleReadings to database
* updateVehicleReading methods adds one vehicleReading to database
* findAllVehicleReadings return list of all vehicles Readings
* findVehicleReading returns vehicle reading requested by vin
* deleteVehicleReading deletes the vehicle readings with vin given as path variable
* */
@RestController
@CrossOrigin
public class VehicleReadingsController {

    @Autowired
    private VehicleReadingsService vehicleReadingsService;

    /*
    * mapped to /reading this method takes vehicleReading in request body by POST and JSON
    * and adds it to database
    * */
    @RequestMapping(method = RequestMethod.POST, value = "/reading")
    public void updateVehicleReading(@RequestBody VehicleReading vehicleReading){
        List<VehicleReading> vehicleReadingList = new ArrayList<>();
        vehicleReadingList.add(vehicleReading);
        vehicleReadingsService.saveReadings(vehicleReadingList);}

    /*
    * mapped to /readings this method takes List of vehicleReading in request body by POST and JSON
    * and adds it to database
    * */
    @RequestMapping(method = RequestMethod.POST, value = "/readings")
    public void updateVehicleReadings(@RequestBody List<VehicleReading> vehicleReadingList){
        vehicleReadingsService.saveReadings(vehicleReadingList);}

    /*
    * mapped to GET /readings/find this method returns list of all vehicleReading in JSON
    * */
    @RequestMapping(method = RequestMethod.GET, value = "/readings/find")
    public List<VehicleReading> findAllVehicleReadings(){ return vehicleReadingsService.findAllReadings(); }
    /*
    * mapped to /readings/ this method returns list of all vehicleReading for a vin in path variable by GET
    * */
    @RequestMapping(method = RequestMethod.GET, value = "/readings/find/{vin}")
    public List<VehicleReading> findVehicleReading(@PathVariable("vin") String vin){
        return vehicleReadingsService.findOneVehicleReadings(vin);
    }

    /*
    * DELETEs all readings of vehicle corresponding to vin
    * */
    @RequestMapping(method = RequestMethod.DELETE, value = "/readings/delete/{vin}")
    public void deleteVehicleReadings(@PathVariable String vin){
        vehicleReadingsService.deleteVehicleReadings(vin);
    }

    /*
    * DELETEs one vehicel reading by taking vehicle reading in request body
    * */
    @RequestMapping(method = RequestMethod.DELETE, value = "/readings/delete")
    public void deleteOneVehicleReading(@RequestBody VehicleReading vehicleReading){
        vehicleReadingsService.deleteOneVehicleReading(vehicleReading);
    }

}
