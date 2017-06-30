package io.egen.training.controller;

import io.egen.training.aspect.BoundaryLogger;
import io.egen.training.entity.Vehicle;
import io.egen.training.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

/*
*VehicleController is a rest controller which maps requests to the request
 * mapping provided and delegates
* work to VehicleService service layer
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

    private VehicleService vehicleService;
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    /*
    * PUTs JSON vehicle in database
    * */
    @BoundaryLogger
    @RequestMapping(method = RequestMethod.PUT, value = "/vehicle")
    public void updateVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.saveVehicles(Collections.singletonList(vehicle));
    }
    /*
    * PUT List of vehicles in JSON to database
    * */
    @BoundaryLogger
    @RequestMapping(method = RequestMethod.PUT, value = "/vehicles")
    public void updateVehicles(@RequestBody List<Vehicle> vehicleList) {
        vehicleService.saveVehicles(vehicleList);
    }
    /*
    * GETs all vehicles in database
    * */
    @BoundaryLogger
    @RequestMapping(method = RequestMethod.GET, value = "/vehicles/find")
    public List<Vehicle> findAllVehicles() {
        return vehicleService.findAllVehicles();
    }
    /*
    * GETs one vehicle by taking vin as path variable
    * */
    @RequestMapping(method = RequestMethod.GET, value = "/vehicles/find/{vin}")
    @BoundaryLogger
    public Vehicle findOneVehicle(@PathVariable("vin") String vin) {
        return vehicleService.findOneVehicle(vin);
    }
    /*
    * DELETEs vehicle using vin as path variable
    * */
    @BoundaryLogger
    @RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/delete/{vin}")
    public void deleteVehicle(@PathVariable("vin") String vin) {
        vehicleService.deleteVehicle(vin);
    }

    @BoundaryLogger
    @RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/delete")
    public void deleteAllVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.deleteVehicle(vehicle.getVin());
    }

    @BoundaryLogger
    @RequestMapping(method = RequestMethod.DELETE, value = "/vehicles/deleteAll")
    public void deleteAll() { vehicleService.deleteAll(); }
}
