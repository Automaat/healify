package com.hereforbeer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private String id;
    private String beaconId;

    private String firstName;
    private String lastName;
    private String address;
    private String pesel;
    private String birthDate;

    private String in;
    private String out;

    private String disease;
    private String doctor;
    private HealthStateDTO healthState;
    private List<CheckUpDTO> checkUps;
    private List<DrugDTO> drugs;
}
