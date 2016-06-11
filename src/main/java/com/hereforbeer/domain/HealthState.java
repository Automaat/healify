package com.hereforbeer.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HealthState {

    private List<Temperature> temperatures = new ArrayList<>();
    private List<Pressure> pressures = new ArrayList<>();
}
