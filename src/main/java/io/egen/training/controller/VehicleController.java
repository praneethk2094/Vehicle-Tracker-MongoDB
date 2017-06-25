package io.egen.training.controller;

import io.egen.training.entity.Vehicle;
import io.egen.training.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/vehicles")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateVehicles(@RequestBody List<Vehicle> vehicleList){vehicleService.saveVehicles(vehicleList);}

    @RequestMapping(method = RequestMethod.GET, value = "/find", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<Vehicle> findAllVehicles(){
        return vehicleService.findAllVehicles();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find/{vin}")
    public Vehicle findVehicle(@PathVariable("vin") String vin){
        return vehicleService.findOneVehicle(vin);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public void deleteVehicle(@RequestBody Vehicle vehicle){
        vehicleService.deleteVehicle(vehicle);
    }
}
