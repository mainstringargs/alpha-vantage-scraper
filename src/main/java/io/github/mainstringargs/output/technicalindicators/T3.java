package io.github.mainstringargs.output.technicalindicators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.github.mainstringargs.input.technicalindicators.Interval;
import io.github.mainstringargs.output.JsonParser;
import io.github.mainstringargs.output.technicalindicators.data.IndicatorData;

/**
 * Representation of the triple exponential moving average (T3) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class T3 extends TechnicalIndicatorResponse<IndicatorData> {

    private T3(final Map<String, String> metaData, final List<IndicatorData> indicatorData) {
        super(metaData, indicatorData);
    }

    /**
     * Creates T3 instance from json.
     *
     * @param interval specifies how to interpret the date key to the data json object
     * @param json string to parse
     * @return T3 instance
     */
    public static T3 from(Interval interval, String json) {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code T3}.
     *
     * @see TechnicalIndicatorParser
     * @see JsonParser
     */
    private static class Parser extends TechnicalIndicatorParser<T3> {

        public Parser(Interval interval) {
            super(interval);
        }

        @Override
        String getIndicatorKey() {
            return "Technical Analysis: T3";
        }

        @Override
        T3 resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
            List<IndicatorData> indicators = new ArrayList<>();
            indicatorData.forEach(
                            (key, values) -> indicators.add(new IndicatorData(resolveDate(key),
                                            Double.parseDouble(values.get("T3")))));
            return new T3(metaData, indicators);
        }
    }
}

