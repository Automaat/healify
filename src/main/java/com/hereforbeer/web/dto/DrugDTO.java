package com.hereforbeer.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrugDTO {

    private String date;
    private String name;
    private String unit;
    private int quantity;
}
