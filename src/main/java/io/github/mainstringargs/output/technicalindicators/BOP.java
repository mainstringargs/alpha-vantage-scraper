package io.github.mainstringargs.output.technicalindicators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.github.mainstringargs.input.technicalindicators.Interval;
import io.github.mainstringargs.output.JsonParser;
import io.github.mainstringargs.output.technicalindicators.data.IndicatorData;

/**
 * Representation of the balance of power (BOP) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class BOP extends TechnicalIndicatorResponse<IndicatorData> {

    private BOP(final Map<String, String> metaData, final List<IndicatorData> indicators) {
        super(metaData, indicators);
    }

    /**
     * Creates {@code BOP} instance from json.
     *
     * @param interval specifies how to interpret the date key to the data json object
     * @param json string to parse
     * @return BOP instance
     */
    public static BOP from(Interval interval, String json) {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code BOP}.
     *
     * @see TechnicalIndicatorParser
     * @see JsonParser
     */
    private static class Parser extends TechnicalIndicatorParser<BOP> {

        public Parser(Interval interval) {
            super(interval);
        }

        @Override
        String getIndicatorKey() {
            return "Technical Analysis: BOP";
        }

        @Override
        BOP resolve(Map<String, String> metaData, Map<String, Map<String, String>> indicatorData) {
            List<IndicatorData> indicators = new ArrayList<>();
            indicatorData.forEach(
                            (key, values) -> indicators.add(new IndicatorData(resolveDate(key),
                                            Double.parseDouble(values.get("BOP")))));
            return new BOP(metaData, indicators);
        }
    }
}
