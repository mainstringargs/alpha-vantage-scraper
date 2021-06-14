package io.github.mainstringargs.alphavantagescraper.output.technicalindicators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.github.mainstringargs.alphavantagescraper.input.technicalindicators.Interval;
import io.github.mainstringargs.alphavantagescraper.output.AlphaVantageException;
import io.github.mainstringargs.alphavantagescraper.output.JsonParser;
import io.github.mainstringargs.alphavantagescraper.output.technicalindicators.data.IndicatorData;

/**
 * Representation of exponential moving average (EMA) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class EMA extends TechnicalIndicatorResponse<IndicatorData> {

    private EMA(final Map<String, String> metaData, final List<IndicatorData> indicatorData) {
        super(metaData, indicatorData);
    }

    /**
     * Creates {@code EMA} instance from json.
     *
     * @param interval specifies how to interpret the date key to the data json object
     * @param json string to parse
     * @return EMA instance
     */
    public static EMA from(Interval interval, String json) {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code EMA}.
     *
     * @see TechnicalIndicatorParser
     * @see JsonParser
     */
    private static class Parser extends TechnicalIndicatorParser<EMA> {

        public Parser(Interval interval) {
            super(interval);
        }

        @Override
        String getIndicatorKey() {
            return "Technical Analysis: EMA";
        }

        @Override
        EMA resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData)
                        throws AlphaVantageException {
            List<IndicatorData> indicators = new ArrayList<>();
            indicatorData.forEach(
                            (key, values) -> indicators.add(new IndicatorData(resolveDate(key),
                                            Double.parseDouble(values.get("EMA")))));
            return new EMA(metaData, indicators);
        }
    }
}
