package com.hereforbeer.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckUpDTO {

    private String name;
    private String date;
}
