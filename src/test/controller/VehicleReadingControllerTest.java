package controller;

import io.egen.training.Application;
import io.egen.training.entity.Tires;
import io.egen.training.entity.VehicleReading;
import io.egen.training.service.VehicleReadingsService;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class VehicleReadingControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    private VehicleReading vehicleReading;
    private List<VehicleReading> vehicleReadings = new ArrayList<>();
    @Mock
    private Tires tires;
    @Autowired
    private VehicleReadingsService vehicleReadingsService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        vehicleReadings = vehicleReadingsService.findAllReadings();
        vehicleReadingsService.deleteAll();
        this.vehicleReading = new VehicleReading();
        vehicleReading.setCheckEngineLightOn(true);
        vehicleReading.setVin("testVin");
        vehicleReading.setTires(tires);
        List<VehicleReading> vehicleReadingList = new ArrayList<>();
        vehicleReadingList.add(vehicleReading);
        vehicleReadingsService.saveReadings(vehicleReadingList);
    }


    @Test
    public void testSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/readings/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("testVin")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].checkEngineLightOn", Matchers.is(true)));
    }

    @Test
    public void testFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].checkEngineLightOn", Matchers.is(false)));
    }

    @After
    public void refil() {
        vehicleReadingsService.deleteAll();
        vehicleReadingsService.saveReadings(vehicleReadings);
    }

}