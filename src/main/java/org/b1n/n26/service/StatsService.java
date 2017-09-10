package org.b1n.n26.service;

import org.b1n.n26.model.StatsModel;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    public StatsModel getStats() {
        // nothing yet
        return StatsModel.ZERO;
    }
}
