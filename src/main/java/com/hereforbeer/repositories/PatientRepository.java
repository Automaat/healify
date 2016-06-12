package com.hereforbeer.repositories;

import com.hereforbeer.domain.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    Optional<Patient> findOneByBeaconId(String beaconId);

    List<Patient> findByBeaconIdIn(List<String> ids);

    Optional<Patient> findOneById(String id);


    long countByOutBefore(LocalDateTime date);
}
