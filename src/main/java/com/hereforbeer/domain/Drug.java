package com.hereforbeer.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Drug {

    private LocalDateTime date;
    private String name;
    private int quantity;
    private String unit;
}
