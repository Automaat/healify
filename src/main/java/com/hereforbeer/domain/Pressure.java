package com.hereforbeer.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Pressure {

    private LocalDateTime date;
    private int value;

    public Pressure(int value){
        this.date = LocalDateTime.now();
        this.value = value;
    }
}
