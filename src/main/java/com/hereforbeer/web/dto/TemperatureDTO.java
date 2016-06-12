package com.hereforbeer.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemperatureDTO {

    private String date;
    private String value;
}
