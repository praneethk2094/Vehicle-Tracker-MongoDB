package io.egen.training.controller;

import io.egen.training.entity.Vehicle;
import io.egen.training.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
/*
*VehicleController is a rest controller which maps requests to the request mapping provided and delegates
*work to VehicleService service layer
*
* updateVehicles method adds list of vehicle to database
* updateVehicle methods adds one vehicle to database
* findAllVehicles return list of all vehicles
* findVehicle returns vehicle requested
* deleteVehicle deletes the vehicle in database with given vin
* */
@CrossOrigin
@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /*
    * PUTs JSON vehicle in database
    * */
    @RequestMapping(method = RequestMethod.PUT, value = "/vehicle")
    public void updateVehicle(@RequestBody Vehicle vehicle){
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle);
        vehicleService.saveVehicles(vehicleList);
    }

    /*
    * PUT List of vehicles in JSON to database
    * */
    @RequestMapping(method = RequestMethod.PUT, value = "/vehicles")
    public void updateVehicles(@RequestBody List<Vehicle> vehicleList){vehicleService.saveVehicles(vehicleList);}

    /*
    * GETs all vehicles in database
    * */
    @RequestMapping(method = RequestMethod.GET, value = "/find")
    public List<Vehicle> findAllVehicles(){
        return vehicleService.findAllVehicles();
    }

    /*
    * GETs one vehicle by taking vin as path variable
    * */
    @RequestMapping(method = RequestMethod.GET, value = "/find/{vin}")
    public Vehicle findVehicle(@PathVariable("vin") String vin){
        return vehicleService.findOneVehicle(vin);
    }

    /*
    * DELETEs vehicle using vin as path variable
    * */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{vin}")
    public void deleteVehicle(@PathVariable("vin") String vin){
        vehicleService.deleteVehicle(vin);
    }

}
