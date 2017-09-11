package org.b1n.n26.service;

import org.b1n.n26.model.StatsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    private StorageService storage;

    public StatsModel getStats() {
        return storage.getStats();
    }
}
