package io.egen.training.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
/*
* Alerts class POJO
* Uses Lombok to generate GETTERS and SETTERS
* indexes vin field for better to make queries quick
* */
@Data
@Document
public class Alerts {
    public enum Alert{
        HIGH, MEDIUM, LOW
    }
    @Indexed
    private String vin;
    private Date timestamp;
    private Alert engineRpmAlert;
    private Alert fuelVolumeAlert;
    private Alert tirePressureAlert;
    private Alert engineCoolantAlert;
    private Alert checkEngineLightOnAlert;
}
