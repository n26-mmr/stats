package org.b1n.n26.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TransactionModel {

    public static final Long MAX_TIME_TO_CONSIDER_IN_MILLIS = 60000L;

    private Long timestamp;

    private Double amount;

    public TransactionModel() {
        // ...
    }

    public TransactionModel(Double amount, Long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    @JsonIgnore
    public boolean isTooOld() {
        return this.timestamp < System.currentTimeMillis() - MAX_TIME_TO_CONSIDER_IN_MILLIS;
    }
}
