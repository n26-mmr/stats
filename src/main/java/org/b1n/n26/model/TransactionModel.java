package org.b1n.n26.model;

public class TransactionModel {
    private final Long timestamp;

    private final Double amount;

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
}
