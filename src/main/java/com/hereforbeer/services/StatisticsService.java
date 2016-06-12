package com.hereforbeer.services;

import com.hereforbeer.repositories.PatientRepository;
import com.hereforbeer.web.dto.stats.UsersCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class StatisticsService {

    private final PatientRepository patientRepository;

    @Autowired
    public StatisticsService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public UsersCountDTO getBasicUsersCount() {

        long allUsersCount = patientRepository.count();

        double allUsersCheckedOut = patientRepository.countByOutBefore(LocalDateTime.now()) / (double) allUsersCount;

        double allUsersCheckedIn = 1 - allUsersCheckedOut;

        return new UsersCountDTO(round(allUsersCheckedIn, 2), round(allUsersCheckedOut, 2));
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
