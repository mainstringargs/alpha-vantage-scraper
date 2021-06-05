package io.github.mainstringargs.output.technicalindicators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.github.mainstringargs.input.technicalindicators.Interval;
import io.github.mainstringargs.output.JsonParser;
import io.github.mainstringargs.output.technicalindicators.data.IndicatorData;

/**
 * Representation of the midpoint (MIDPOINT) response from api. MIDPOINT = (highest value + lowest
 * value)/2.
 *
 * @see TechnicalIndicatorResponse
 */
public class MIDPOINT extends TechnicalIndicatorResponse<IndicatorData> {

    private MIDPOINT(final Map<String, String> metaData, final List<IndicatorData> indicators) {
        super(metaData, indicators);
    }

    /**
     * Creates {@code MIDPOINT} instance from json.
     *
     * @param interval specifies how to interpret the date key to the data json object
     * @param json string to parse
     * @return MIDPOINT instance
     */
    public static MIDPOINT from(Interval interval, String json) {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code MIDPOINT}.
     *
     * @see TechnicalIndicatorParser
     * @see JsonParser
     */
    private static class Parser extends TechnicalIndicatorParser<MIDPOINT> {

        public Parser(Interval interval) {
            super(interval);
        }

        @Override
        String getIndicatorKey() {
            return "Technical Analysis: MIDPOINT";
        }

        @Override
        MIDPOINT resolve(Map<String, String> metaData,
                        Map<String, Map<String, String>> indicatorData) {
            List<IndicatorData> indicators = new ArrayList<>();
            indicatorData.forEach(
                            (key, values) -> indicators.add(new IndicatorData(resolveDate(key),
                                            Double.parseDouble(values.get("MIDPOINT")))));
            return new MIDPOINT(metaData, indicators);
        }
    }
}
