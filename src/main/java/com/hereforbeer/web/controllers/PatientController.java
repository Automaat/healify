package com.hereforbeer.web.controllers;

import com.hereforbeer.domain.Patient;
import com.hereforbeer.services.PatientService;
import com.hereforbeer.web.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patients", method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> registerPatient(@RequestBody PatientDTO patientDTO) {

        String id = patientService.registerPatient(patientDTO);

        return new ResponseEntity<>(PatientDTO.builder().id(id).build(), CREATED);
    }

    @RequestMapping(value = "/patients/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> getPatientByBeaconId(@PathVariable("id") String beaconId) {

        return new ResponseEntity<>(patientService.getPatientByBeaconId(beaconId), OK);
    }

    @RequestMapping(value = "/patients/with-ids", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDTO>> getPatientsWithIds(@RequestBody List<String> beaconIds) {

        return new ResponseEntity<>(patientService.getAllPatientsWithIds(beaconIds), OK);
    }

    @RequestMapping(value = "/patients/{id}/check-out", method = DELETE)
    public ResponseEntity<?> checkOutPatient(@PathVariable("id") String beaconId) {

        patientService.checkOutPatient(beaconId);

        return new ResponseEntity<>(OK);
    }

    @RequestMapping(value = "/patients", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDTO>> getAllPatients() {

        return new ResponseEntity<>(patientService.getAllPatients(), OK);
    }

    @RequestMapping(value = "/patients", params = "id", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> getPatientByDatabaseId(@RequestParam("id") String id){

        return new ResponseEntity<>(patientService.getById(id), OK);
    }

}
