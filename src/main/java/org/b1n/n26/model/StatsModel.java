package org.b1n.n26.model;

public class StatsModel {

    public static final StatsModel ZERO = new StatsModel(0d, 0d, 0d, 0d, 0L);

    private final Double sum, avg, max, min;

    private final Long count;

    public StatsModel(final Double sum, final Double avg, final Double max, final Double min, final Long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public Double getSum() {
        return sum;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Long getCount() {
        return count;
    }
}
