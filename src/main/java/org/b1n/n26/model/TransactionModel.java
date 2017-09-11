package org.b1n.n26.model;

import java.time.Instant;

public class TransactionModel {

    private static final Long MAX_TIME_TO_CONSIDER_IN_MILLIS = 60000L;

    private Long timestamp;

    private Double amount;

    public TransactionModel() {
        // ...
    }

    public TransactionModel(Long timestamp, Double amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public boolean isTooOld() {
        return this.timestamp < System.currentTimeMillis() - MAX_TIME_TO_CONSIDER_IN_MILLIS;
    }
}
