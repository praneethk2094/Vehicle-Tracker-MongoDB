package io.egen.training.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Tires class POJO
* Uses Lombok to generate GETTERS and SETTERS
* */
@Data
public class Tires {
    private byte frontLeft;
    private byte frontRight;
    private byte rearLeft;
    private byte rearRight;

    /*
    * returns List of tire pressures to caller
    * */
    @Transient
    @JsonIgnore
    public List<Byte> getTirePressures() {
        return new ArrayList<>(Arrays.asList(frontLeft, frontRight, rearLeft, rearRight));
    }
}
