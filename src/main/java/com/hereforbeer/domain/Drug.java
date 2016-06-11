package com.hereforbeer.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Drug {

    private LocalDateTime date;
    private String name;
    private int quantity;
    private String unit;
}
