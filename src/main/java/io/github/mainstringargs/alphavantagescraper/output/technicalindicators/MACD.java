package io.github.mainstringargs.alphavantagescraper.output.technicalindicators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.github.mainstringargs.alphavantagescraper.input.technicalindicators.Interval;
import io.github.mainstringargs.alphavantagescraper.output.JsonParser;
import io.github.mainstringargs.alphavantagescraper.output.technicalindicators.data.MACDData;

/**
 * Representation of moving average converge/divergence (MACD) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class MACD extends TechnicalIndicatorResponse<MACDData> {

    private MACD(final Map<String, String> metaData, final List<MACDData> indicatorData) {
        super(metaData, indicatorData);
    }

    /**
     * Creates {@code MACD} instance from json.
     *
     * @param interval specifies how to interpret the date key to the data json object
     * @param json string to parse
     * @return MACD instance
     */
    public static MACD from(Interval interval, String json) {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code MACD}.
     *
     * @see TechnicalIndicatorParser
     * @see JsonParser
     */
    private static class Parser extends TechnicalIndicatorParser<MACD> {

        public Parser(Interval interval) {
            super(interval);
        }

        @Override
        String getIndicatorKey() {
            return "Technical Analysis: MACD";
        }

        @Override
        MACD resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
            List<MACDData> indicators = new ArrayList<>();
            indicatorData.forEach((key,
                            values) -> indicators.add(new MACDData(resolveDate(key),
                                            Double.parseDouble(values.get("MACD_Signal")),
                                            Double.parseDouble(values.get("MACD_Hist")),
                                            Double.parseDouble(values.get("MACD")))));
            return new MACD(metaData, indicators);
        }
    }
}
