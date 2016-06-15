package com.hereforbeer.web.controllers;

import com.hereforbeer.services.StatisticsService;
import com.hereforbeer.web.dto.stats.UsersCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping(value = "/stats/basic")
    public ResponseEntity<UsersCountDTO> getBasicUsersCounts() {

        return new ResponseEntity<>(statisticsService.getBasicUsersCount(), OK);
    }
}
