package io.github.mainstringargs.alphavantagescraper.input.technicalindicators;

import io.github.mainstringargs.alphavantagescraper.input.ApiParameter;

public class FastPeriod implements ApiParameter {
    private final String period;

    private FastPeriod(String period) {
        this.period = period;
    }

    public static FastPeriod of(int time) {
        assert time > 0;
        return new FastPeriod(String.format("%d", time));
    }

    @Override
    public String getKey() {
        return "fastperiod";
    }

    @Override
    public String getValue() {
        return period;
    }
}
