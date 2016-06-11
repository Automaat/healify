package com.hereforbeer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Temperature {

    private LocalDateTime date;
    private int value;

    public Temperature(int value){
        this.date = LocalDateTime.now();
        this.value = value;
    }

}
