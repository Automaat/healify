package com.hereforbeer.services;

import com.hereforbeer.domain.HealthState;
import com.hereforbeer.domain.Patient;
import com.hereforbeer.domain.Pressure;
import com.hereforbeer.domain.Temperature;
import com.hereforbeer.repositories.PatientRepository;
import com.hereforbeer.web.BadRequestException;
import com.hereforbeer.web.dto.DTOMappers;
import com.hereforbeer.web.dto.HealthStateDTO;
import com.hereforbeer.web.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hereforbeer.web.ErrorInfo.PATIENT_NOT_FOUND;
import static java.util.stream.Collectors.toList;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public String registerPatient(PatientDTO patientDTO) {

        Patient patient = Patient.builder()
                .beaconId(patientDTO.getBeaconId())
                .firstName(patientDTO.getFirstName())
                .lastName(patientDTO.getLastName())
                .address(patientDTO.getAddress())
                .doctor(patientDTO.getDoctor())
                .disease(patientDTO.getDisease())
                .in(LocalDateTime.now())
                .pesel(patientDTO.getPesel())
                .birthDate(getDateFromPesel(patientDTO.getPesel()))
                .build();

        patientRepository.save(patient);

        return patient.getId();
    }

    public PatientDTO getPatientByBeaconId(String beaconId) {

        return patientRepository.findOneByBeaconId(beaconId)
                .map(DTOMappers::parsePatientDTOFromPatient)
                .orElseThrow(() -> new BadRequestException(PATIENT_NOT_FOUND));
    }

    public HealthStateDTO getPatientHealthState(String beaconId) {

        return patientRepository.findOneByBeaconId(beaconId)
                .map(p -> DTOMappers.parseHealthSateFromPatient(p.getHealthState()))
                .orElseThrow(() -> new BadRequestException(PATIENT_NOT_FOUND));
    }

    private LocalDate getDateFromPesel(String pesel) {
        if (pesel != null){
            String dateFromPesel = pesel.substring(0, 6);

            int year = Integer.parseInt(dateFromPesel.substring(0, 2));
            int month = Integer.parseInt(dateFromPesel.substring(2, 4));
            int day = Integer.parseInt(dateFromPesel.substring(4, 6));

            return LocalDate.of(year, month, day);
        } else {
            return LocalDate.now();
        }
    }


    public void updatePatientTemperature(String beaconId, Integer value) {

        Optional<Patient> patient = patientRepository.findOneByBeaconId(beaconId);

        patient.ifPresent(p -> {
            HealthState healthState = p.getHealthState();
            if (healthState == null) {
                healthState = new HealthState();
                p.setHealthState(healthState);
            }

            healthState.getTemperatures().add(new Temperature(value));


            patientRepository.save(p);
        });
    }

    public void updatePatientPressure(String beaconId, Integer value) {

        Optional<Patient> patient = patientRepository.findOneByBeaconId(beaconId);

        patient.ifPresent(p -> {
            p.getHealthState()
                    .getPressures()
                    .add(new Pressure(value));
            patientRepository.save(p);
        });
    }

    public List<PatientDTO> getAllPatientsWithIds(List<String> beaconIds) {

        return patientRepository.findByBeaconIdIn(beaconIds)
                .stream()
                .map(DTOMappers::parsePatientDTOFromPatient)
                .collect(toList());
    }
}
