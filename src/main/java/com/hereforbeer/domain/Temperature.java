package com.hereforbeer.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Temperature {

    private LocalDateTime date;
    private double value;

    public Temperature(double value) {
        this.date = LocalDateTime.now();
        this.value = value;
    }

}
