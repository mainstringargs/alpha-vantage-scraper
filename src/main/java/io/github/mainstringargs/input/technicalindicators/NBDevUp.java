package io.github.mainstringargs.input.technicalindicators;

import io.github.mainstringargs.input.ApiParameter;

public class NBDevUp implements ApiParameter {
    private final String multiplier;

    private NBDevUp(String period) {
        this.multiplier = period;
    }

    public static NBDevUp of(int multiplier) {
        assert multiplier > 0;
        return new NBDevUp(String.format("%d", multiplier));
    }

    @Override
    public String getKey() {
        return "nbdevup";
    }

    @Override
    public String getValue() {
        return multiplier;
    }
}
