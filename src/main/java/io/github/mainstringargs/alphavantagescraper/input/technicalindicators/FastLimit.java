package io.github.mainstringargs.alphavantagescraper.input.technicalindicators;

import io.github.mainstringargs.alphavantagescraper.input.ApiParameter;

public class FastLimit implements ApiParameter {
    private final String limit;

    private FastLimit(String limit) {
        this.limit = limit;
    }

    public static FastLimit of(float limit) {
        assert limit > 0.0;
        return new FastLimit(String.format("%.2f", limit));
    }

    @Override
    public String getKey() {
        return "fastlimit";
    }

    @Override
    public String getValue() {
        return limit;
    }
}
