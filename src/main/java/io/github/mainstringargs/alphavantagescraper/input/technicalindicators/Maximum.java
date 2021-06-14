package io.github.mainstringargs.alphavantagescraper.input.technicalindicators;

import io.github.mainstringargs.alphavantagescraper.input.ApiParameter;

public class Maximum implements ApiParameter {
    private final String maximum;

    private Maximum(String maximum) {
        this.maximum = maximum;
    }

    public static Maximum of(float maximum) {
        assert maximum > 0.0;
        return new Maximum(String.format("%.2f", maximum));
    }

    @Override
    public String getKey() {
        return "maximum";
    }

    @Override
    public String getValue() {
        return maximum;
    }
}

