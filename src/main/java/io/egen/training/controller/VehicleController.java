package io.egen.training.controller;

import io.egen.training.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/vehicles")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.PUT, consume = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateVehicleCollection(){
        System.out.println("hello test");
    }
}
