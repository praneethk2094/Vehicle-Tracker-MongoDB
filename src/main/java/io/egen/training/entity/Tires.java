package io.egen.training.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class Tires{
    private byte frontLeft;
    private byte frontRight;
    private byte rearLeft;
    private byte rearRight;
    @Transient
    private List<Byte> tirePressures = new ArrayList<>();

    public List<Byte> getTirePressures() {
        tirePressures.clear();
        tirePressures = new ArrayList<>(Arrays.asList(frontLeft, frontRight, rearLeft, rearRight));
        return tirePressures;
    }
}
