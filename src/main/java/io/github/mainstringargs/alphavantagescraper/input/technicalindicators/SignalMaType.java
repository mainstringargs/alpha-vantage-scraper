package io.github.mainstringargs.alphavantagescraper.input.technicalindicators;

import io.github.mainstringargs.alphavantagescraper.input.ApiParameter;

public enum SignalMaType implements ApiParameter {
    SMA(0), EMA(1), WMA(2), DEMA(3), TEMA(4), TRIMA(5), T3(6), KAMA(7), MAMA(8);

    private int type;

    SignalMaType(int type) {
        this.type = type;
    }

    @Override
    public String getKey() {
        return "slowmatype";
    }

    @Override
    public String getValue() {
        return String.valueOf(type);
    }
}

