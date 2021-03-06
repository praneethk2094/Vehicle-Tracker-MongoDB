package controller;

import io.egen.training.controller.AlertsController;
import io.egen.training.entity.Alerts;
import io.egen.training.entity.Vehicle;
import io.egen.training.entity.VehicleReading;
import io.egen.training.service.AlertsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/*
* Mocks and tests create Alerts with Mockito
* */
public class AlertsControllerTest {
    private AlertsController alertsController;
    private AlertsService alertsService;
    private Alerts alertsSuccess;
    private Alerts alertsFail;
    @Mock
    private Vehicle vehicle;
    @Mock
    private VehicleReading vehicleReading;

    @Before
    public void setUp() {
        alertsController = new AlertsController(alertsService);
        alertsService = Mockito.mock(AlertsService.class);
        alertsSuccess = new Alerts();
        alertsSuccess.setAlertId("testID");
        alertsFail = new Alerts();
        alertsFail.setAlertId("test");
    }

    @Test
    public void testAlertsSuccess() {
        Mockito.when(alertsService.createAlerts(vehicle, vehicleReading)).thenReturn(alertsSuccess);
        Assert.assertEquals(alertsService.createAlerts(vehicle, vehicleReading), alertsSuccess);
        Mockito.verify(alertsService).createAlerts(vehicle, vehicleReading);
    }

    @Test
    public void testAlertsFail() {
        Mockito.when(alertsService.createAlerts(vehicle, vehicleReading)).thenReturn(alertsSuccess);
        Assert.assertEquals(alertsService.createAlerts(vehicle, vehicleReading), alertsFail);
        Mockito.verify(alertsService).createAlerts(vehicle, vehicleReading);
    }
}