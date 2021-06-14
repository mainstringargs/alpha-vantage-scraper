package io.github.mainstringargs.alphavantagescraper.output.timeseries;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.github.mainstringargs.alphavantagescraper.input.timeseries.Interval;
import io.github.mainstringargs.alphavantagescraper.output.AlphaVantageException;
import io.github.mainstringargs.alphavantagescraper.output.JsonParser;
import io.github.mainstringargs.alphavantagescraper.output.timeseries.data.StockData;

/**
 * Representation of intra day response from api.
 *
 * @see TimeSeriesResponse
 */
public class IntraDay extends TimeSeriesResponse {

    private IntraDay(final Map<String, String> metaData, final List<StockData> stocks) {
        super(metaData, stocks);
    }

    /**
     * Creates {@code IntraDay} instance from json.
     *
     * @param json string to parse
     * @return IntraDay instance
     */
    public static IntraDay from(Interval interval, String json) {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code IntraDay}.
     *
     * @see TimeSeriesParser
     * @see JsonParser
     */
    private static class Parser extends TimeSeriesParser<IntraDay> {
        private final Interval interval;

        Parser(Interval interval) {
            this.interval = interval;
        }

        @Override
        String getStockDataKey() {
            return "Time Series (" + interval.getValue() + ")";
        }

        @Override
        IntraDay resolve(Map<String, String> metaData, Map<String, Map<String, String>> stockData) {
            List<StockData> stocks = new ArrayList<>();
            try {
                stockData.forEach((key, values) -> stocks
                                .add(new StockData(LocalDateTime.parse(key, DATE_WITH_TIME_FORMAT),
                                                Double.parseDouble(values.get("1. open")),
                                                Double.parseDouble(values.get("2. high")),
                                                Double.parseDouble(values.get("3. low")),
                                                Double.parseDouble(values.get("4. close")),
                                                Long.parseLong(values.get("5. volume")))));
            } catch (Exception e) {
                throw new AlphaVantageException("Intraday api change", e);
            }
            return new IntraDay(metaData, stocks);
        }
    }

}
