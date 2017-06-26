package io.egen.training.controller;

import io.egen.training.entity.Vehicle;
import io.egen.training.entity.VehicleReading;
import io.egen.training.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class VehicleReadingsController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.POST, value = "/reading", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateVehicleReadings(@RequestBody VehicleReading vehicleReading){
        List<VehicleReading> vehicleReadingList = new ArrayList<VehicleReading>();
        vehicleReadingList.add(vehicleReading);
        vehicleService.saveReadings(vehicleReadingList);}

    @RequestMapping(method = RequestMethod.POST, value = "/readings", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateVehicleReadings(@RequestBody List<VehicleReading> vehicleReadingList){
        vehicleService.saveReadings(vehicleReadingList);}

    @RequestMapping(method = RequestMethod.GET, value = "/readings/find", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<VehicleReading> findAllVehicleReadings(){ return vehicleService.findAllReadings(); }

    @RequestMapping(method = RequestMethod.GET, value = "/readings/find/{vin}")
    public VehicleReading findVehicleReading(@PathVariable("vin") String vin){
        return vehicleService.findOneReading(vin);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/readings/delete")
    public void deleteVehicleReading(@RequestBody VehicleReading vehicleReading){
        vehicleService.deleteVehicleReading(vehicleReading);
    }

}
