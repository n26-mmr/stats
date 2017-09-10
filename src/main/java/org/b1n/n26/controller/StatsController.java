package org.b1n.n26.controller;

import org.b1n.n26.model.StatsModel;
import org.b1n.n26.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {

    static final String URI_STATS = "/statistics";

    @Autowired
    private StatsService service;

    @GetMapping(path = URI_STATS)
    public ResponseEntity<StatsModel> getStats() {
        return ResponseEntity.ok(service.getStats());
    }
}