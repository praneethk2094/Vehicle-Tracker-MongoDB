package io.egen.training.controller;

import io.egen.training.Application;
import io.egen.training.entity.Vehicle;
import io.egen.training.service.VehicleService;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertNotNull;
/*
* Unit testing Vehicle Controller class with Mockito
* */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class VehicleControllerTest {


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private List<Vehicle> vehicles = new ArrayList<>();

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        HttpMessageConverter mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        Vehicle vehicle = new Vehicle();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        vehicles = vehicleService.findAllVehicles();
        vehicleService.deleteAll();
        vehicle.setMake("testMake");
        vehicle.setVin("testVin");
        List<Vehicle> vehicleListTest = new ArrayList<>();
        vehicleListTest.add(vehicle);
        vehicleService.saveVehicles(vehicleListTest);
        }


    @Test
    public void testSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("testVin")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].make", Matchers.is("testMake")));
    }
    @Test
    public void testFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].make", Matchers.is("test")));
    }
    @After
    public void refil(){
        vehicleService.deleteAll();
        vehicleService.saveVehicles(vehicles);
    }

}