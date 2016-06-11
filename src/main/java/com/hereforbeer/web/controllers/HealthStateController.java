package com.hereforbeer.web.controllers;

import com.hereforbeer.services.PatientService;
import com.hereforbeer.web.dto.HealthStateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

@RestController
@RequestMapping("/api")
public class HealthStateController {

    private final PatientService patientService;

    @Autowired
    public HealthStateController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patients/{id}/health-status", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthStateDTO> getPatientHealthState(@PathVariable("id") String beaconId) {

        return new ResponseEntity<>(patientService.getPatientHealthState(beaconId), OK);
    }

    @RequestMapping(value = "/patients/{id}", params = "temperature", method = PATCH, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePatientTemperature(@PathVariable("id") String beaconId, @RequestParam("temperature") Integer value) {

        patientService.updatePatientTemperature(beaconId, value);

        return new ResponseEntity<>(OK);
    }
}
