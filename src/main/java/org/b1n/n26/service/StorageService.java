package org.b1n.n26.service;

import org.b1n.n26.model.StatsModel;
import org.b1n.n26.model.TransactionModel;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class StorageService {
    private Double sum, avg, max, min;

    private AtomicLong count;

    public StorageService() {
        this.reset();
    }

    void add(TransactionModel t) {
        long curCount = count.incrementAndGet();
        double amount = t.getAmount();
        double curSum = sum + amount;
        double curAvg = curSum / curCount;
        if (amount > max) {
            max = amount;
        }
        if (min == 0 || amount < min) {
            min = amount;
        }
        sum = curSum;
        avg = curAvg;
    }

    StatsModel getStats() {
        return new StatsModel(sum, avg, max, min, count.get());
    }

    public void reset() {
        sum = avg = max = min = 0d;
        count = new AtomicLong(0L);
    }
}
