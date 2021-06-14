package io.github.mainstringargs.alphavantagescraper.input.technicalindicators;

import io.github.mainstringargs.alphavantagescraper.input.ApiParameter;

public class FastKPeriod implements ApiParameter {
    private final String period;

    private FastKPeriod(String period) {
        this.period = period;
    }

    public static FastKPeriod of(int time) {
        assert time > 0;
        return new FastKPeriod(String.format("%d", time));
    }

    @Override
    public String getKey() {
        return "fastkperiod";
    }

    @Override
    public String getValue() {
        return period;
    }
}
