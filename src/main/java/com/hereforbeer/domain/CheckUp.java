package com.hereforbeer.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CheckUp {

    private String name;
    private LocalDateTime date;
}
