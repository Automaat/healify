package com.hereforbeer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrugDTO {

    private String date;
    private String name;
    private String unit;
    private String quantity;
}
