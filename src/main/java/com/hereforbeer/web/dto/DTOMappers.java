package com.hereforbeer.web.dto;

import com.hereforbeer.domain.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class DTOMappers {

    private static DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static DateTimeFormatter formatterLocalDateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public static PatientDTO parsePatientToDTO(Patient p) {
        return PatientDTO.builder()
                .id(p.getId())
                .beaconId(p.getBeaconId())
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

    private static List<DrugDTO> parseDrugListToDrugDTOs(List<Drug> drugs) {
        return drugs.stream()
                .map(DTOMappers::parseDrugToDrugDTO)
                .collect(toList());
    }

    public static DrugDTO parseDrugToDrugDTO(Drug drug) {
        return DrugDTO.builder()
                .name(drug.getName())
                .quantity(drug.getQuantity() + "")
                .unit(drug.getUnit())
                .date(drug.getDate().format(formatterLocalDateTime))
                .build();
    }

    private static List<CheckUpDTO> parseListToCheckUpDTOs(List<CheckUp> checkUps) {
        return checkUps.stream()
                .map(DTOMappers::parseCheckUpToDTO)
                .collect(toList());
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

    public static Drug parseDrugDtoToDrug(DrugDTO drugDTO) {
        return Drug.builder()
                .date(LocalDateTime.now())
                .name(drugDTO.getName())
                .quantity(Integer.parseInt(drugDTO.getQuantity()))
                .unit(drugDTO.getUnit())
                .build();
    }

    public static CheckUp parseDtoToCheckUp(CheckUpDTO checkUpDTO) {
        return CheckUp.builder()
                .name(checkUpDTO.getName())
                .result(checkUpDTO.getResult())
                .date(LocalDateTime.now())
                .build();
    }

    public static CheckUpDTO parseCheckUpToDTO(CheckUp checkUp) {
        return CheckUpDTO.builder()
                .name(checkUp.getName())
                .result(checkUp.getResult())
                .date(checkUp.getDate().format(formatterLocalDateTime))
                .build();
    }

    public static PatientDTO parsePatientToDTOExtended(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId())
                .beaconId(patient.getBeaconId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .address(patient.getAddress())
                .doctor(patient.getDoctor())
                .disease(patient.getDisease())
                .in(patient.getIn().format(formatterLocalDateTime))
                .pesel(patient.getPesel())
                .birthDate(patient.getBirthDate().format(formatterLocalDate))
                .drugs(parseDrugListToDrugDTOs(patient.getDrugs()))
                .checkUps(parseListToCheckUpDTOs(patient.getCheckUps()))
                .healthState(parseHealthSateFromPatient(patient.getHealthState()))
                .build();
    }

    public static List<TemperatureDTO> parseTemperaturesFromPatient(Patient patient) {
        return patient.getHealthState()
                .getTemperatures()
                .stream()
                .map(DTOMappers::parseTemperatureToDTO)
                .collect(toList());
    }

    private static TemperatureDTO parseTemperatureToDTO(Temperature temperature) {
        return TemperatureDTO.builder()
                .date(temperature.getDate().format(formatterLocalDateTime))
                .value(temperature.getValue() + "")
                .build();
    }
}
