package com.hereforbeer.web.controllers;

import com.hereforbeer.services.PatientService;
import com.hereforbeer.web.dto.CheckUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("api")
public class CheckUpController {

    private final PatientService patientService;

    @Autowired
    public CheckUpController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patients/{id}/check-ups", method = PATCH, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> makeCheckUp(@PathVariable("id") String beaconId, @RequestBody CheckUpDTO checkUpDTO){

        patientService.makeCheckUp(beaconId, checkUpDTO);

        return new ResponseEntity<>(OK);
    }

    @RequestMapping(value = "/patients/{id}/check-ups", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CheckUpDTO>> getCheckUpsForPatient(@PathVariable("id") String beaconId){

        return new ResponseEntity<>(patientService.getAllCheckUpsForPatient(beaconId), OK);
    }
}
