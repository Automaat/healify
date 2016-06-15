package com.hereforbeer.web.controllers;

import com.hereforbeer.services.PatientService;
import com.hereforbeer.web.dto.DrugDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

@RestController
@RequestMapping("/api")
public class DrugController {

    private final PatientService patientService;

    @Autowired
    public DrugController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patients/{id}/drugs", method = PATCH, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> giveDrugToPatient(@PathVariable("id") String beaconId, @RequestBody DrugDTO drugDTO) {

        patientService.giveDrugToPatient(beaconId, drugDTO);

        return new ResponseEntity<>(OK);
    }

    @RequestMapping(value = "/patients/{id}/drugs", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DrugDTO>> getAllInjectedDrugsForPatient(@PathVariable("id") String beaconId){

        return new ResponseEntity<>(patientService.getAllInjectedDrugsForPatient(beaconId), OK);
    }
}
