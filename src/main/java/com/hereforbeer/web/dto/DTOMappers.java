package com.hereforbeer.web.dto;

import com.hereforbeer.domain.HealthState;
import com.hereforbeer.domain.Patient;
import com.hereforbeer.domain.Pressure;
import com.hereforbeer.domain.Temperature;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DTOMappers {

    private static DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static DateTimeFormatter formatterLocalDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public static PatientDTO parsePatientDTOFromPatient(Patient p) {
        return PatientDTO.builder()
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .address(p.getAddress())
                .doctor(p.getDoctor())
                .disease(p.getDisease())
                .in(p.getIn().format(formatterLocalDateTime))
                .pesel(p.getPesel())
                .birthDate(p.getBirthDate().format(formatterLocalDate))
                .build();
    }

    public static HealthStateDTO parseHealthSateFromPatient(HealthState healthState) {

        HealthStateDTO healthStateDTO = new HealthStateDTO();

        Optional<Temperature> temperature = healthState.getTemperatures()
                .stream()
                .sorted((x, y) -> {
                    if (x.getDate().isBefore(y.getDate())) {
                        return 1;
                    } else {
                        return -1;
                    }
                })
                .findFirst();

        Optional<Pressure> pressure = healthState.getPressures()
                .stream()
                .sorted((x, y) -> {
                    if (x.getDate().isBefore(y.getDate())) {
                        return 1;
                    } else {
                        return -1;
                    }
                })
                .findFirst();

        temperature.ifPresent(t -> healthStateDTO.setTemperature(t.getValue()));
        pressure.ifPresent(t -> healthStateDTO.setPressure(t.getValue()));

        return healthStateDTO;
    }
}
