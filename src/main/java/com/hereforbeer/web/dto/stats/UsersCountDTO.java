package com.hereforbeer.web.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersCountDTO {

    private double checkedIn;
    private double checkedOut;
}
