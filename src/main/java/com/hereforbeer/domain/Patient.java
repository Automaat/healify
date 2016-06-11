package com.hereforbeer.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder(builderMethodName = "hiddenBuilder")
public class Patient {

    @Id
    private String id;
    private String beaconId;

    private String firstName;
    private String lastName;
    private String address;
    private String pesel;
    private LocalDate birthDate;

    private LocalDateTime in;
    private LocalDateTime out;

    private String disease;
    private String doctor;
    private HealthState healthState = new HealthState();
    private List<CheckUp> checkUps = new ArrayList<>();
    private List<Drug> drugs = new ArrayList<>();

    public static PatientBuilder builder() {
        return hiddenBuilder()
                .id(UUID.randomUUID().toString())
                .healthState(new HealthState())
                .checkUps(new ArrayList<>())
                .drugs(new ArrayList<>());
    }
}
