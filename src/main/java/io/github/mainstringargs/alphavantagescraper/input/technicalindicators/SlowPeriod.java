package io.github.mainstringargs.alphavantagescraper.input.technicalindicators;

import io.github.mainstringargs.alphavantagescraper.input.ApiParameter;

public class SlowPeriod implements ApiParameter {
    private final String period;

    private SlowPeriod(String period) {
        this.period = period;
    }

    public static SlowPeriod of(int time) {
        assert time > 0;
        return new SlowPeriod(String.format("%d", time));
    }

    @Override
    public String getKey() {
        return "slowperiod";
    }

    @Override
    public String getValue() {
        return period;
    }
}
