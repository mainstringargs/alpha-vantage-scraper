package io.github.mainstringargs.alphavantagescraper.output.technicalindicators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.github.mainstringargs.alphavantagescraper.input.technicalindicators.Interval;
import io.github.mainstringargs.alphavantagescraper.output.JsonParser;
import io.github.mainstringargs.alphavantagescraper.output.technicalindicators.data.IndicatorData;

/**
 * Representation of the rate of change (ROC) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class ROC extends TechnicalIndicatorResponse<IndicatorData> {

    private ROC(final Map<String, String> metaData, final List<IndicatorData> indicators) {
        super(metaData, indicators);
    }

    /**
     * Creates {@code ROC} instance from json.
     *
     * @param interval specifies how to interpret the date key to the data json object
     * @param json string to parse
     * @return ROC instance
     */
    public static ROC from(Interval interval, String json) {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code ROC}.
     *
     * @see TechnicalIndicatorParser
     * @see JsonParser
     */
    private static class Parser extends TechnicalIndicatorParser<ROC> {

        public Parser(Interval interval) {
            super(interval);
        }

        @Override
        String getIndicatorKey() {
            return "Technical Analysis: ROC";
        }

        @Override
        ROC resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
            List<IndicatorData> indicators = new ArrayList<>();
            indicatorData.forEach(
                            (key, values) -> indicators.add(new IndicatorData(resolveDate(key),
                                            Double.parseDouble(values.get("ROC")))));
            return new ROC(metaData, indicators);
        }
    }
}
