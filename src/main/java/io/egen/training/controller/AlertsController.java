package io.egen.training.controller;

import io.egen.training.aspect.BoundaryLogger;
import io.egen.training.entity.Alerts;
import io.egen.training.service.AlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
* Alerts controller provides methods
* findAll to find all alerts in database
* findAlertsByVin to find a vehicles alerts
* */
@CrossOrigin
@RestController
@RequestMapping(value = "/alerts")
public class AlertsController {

    private AlertsService alertsService;
    @Autowired
    public AlertsController(AlertsService alertsService){
        this.alertsService = alertsService;
    }
    /*
        * return list of all alerts in database
        * */
    @BoundaryLogger
    @RequestMapping(method = RequestMethod.GET)
    public List<Alerts> findAll() {
        return alertsService.findAll();
    }

    /*
    * takes path variable VIN
    * returns list of alerts associated to that VIN
    * */
    @BoundaryLogger
    @RequestMapping(method = RequestMethod.GET, value = "/{vin}")
    public List<Alerts> findAlertsByVin(@PathVariable("vin") String vin) {
        return alertsService.findAllByVin(vin);
    }
}
