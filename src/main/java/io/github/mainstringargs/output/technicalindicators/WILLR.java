package io.github.mainstringargs.output.technicalindicators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.github.mainstringargs.input.technicalindicators.Interval;
import io.github.mainstringargs.output.AlphaVantageException;
import io.github.mainstringargs.output.JsonParser;
import io.github.mainstringargs.output.technicalindicators.data.IndicatorData;

/**
 * Representation of the Williams' %R (WILLR) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class WILLR extends TechnicalIndicatorResponse<IndicatorData> {

    private WILLR(final Map<String, String> metaData, final List<IndicatorData> indicatorData) {
        super(metaData, indicatorData);
    }

    /**
     * Creates {@code WILLR} instance from json.
     *
     * @param interval specifies how to interpret the date key to the data json object
     * @param json string to parse
     * @return WILLR instance
     */
    public static WILLR from(Interval interval, String json) {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code WILLR}.
     *
     * @see TechnicalIndicatorParser
     * @see JsonParser
     */
    private static class Parser extends TechnicalIndicatorParser<WILLR> {

        public Parser(Interval interval) {
            super(interval);
        }

        @Override
        String getIndicatorKey() {
            return "Technical Analysis: WILLR";
        }

        @Override
        WILLR resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData)
                        throws AlphaVantageException {
            List<IndicatorData> indicators = new ArrayList<>();
            indicatorData.forEach(
                            (key, values) -> indicators.add(new IndicatorData(resolveDate(key),
                                            Double.parseDouble(values.get("WILLR")))));
            return new WILLR(metaData, indicators);
        }
    }
}

