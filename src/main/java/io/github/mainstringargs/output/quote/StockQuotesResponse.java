package io.github.mainstringargs.output.quote;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Map;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import io.github.mainstringargs.StockQuotes;
import io.github.mainstringargs.output.AlphaVantageException;
import io.github.mainstringargs.output.JsonParser;
import io.github.mainstringargs.output.quote.data.StockQuote;

/**
 * Representation of stock quote response from api.
 *
 * @see StockQuotes
 */
public class StockQuotesResponse {
    private final StockQuote stockQuote;

    private StockQuotesResponse(final StockQuote stockQuote) {
        this.stockQuote = stockQuote;
    }

    /**
     * Gets the StockQuote
     *
     * @return the {@link StockQuote}
     */
    public StockQuote getStockQuote() {
        return stockQuote;
    }

    /**
     * Creates {@code StockQuotesResponse} instance from json.
     *
     * @param json string to parse
     * @return StockQuotesResponse instance
     */
    public static StockQuotesResponse from(String json) {
        Parser parser = new Parser();
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code StockQuotesResponse}.
     *
     * @see JsonParser
     */
    private static class Parser extends JsonParser<StockQuotesResponse> {

        @Override
        protected StockQuotesResponse resolve(final JsonObject rootObject) {
            Type dataType = new TypeToken<Map<String, String>>() {}.getType();
            try {
                Map<String, String> stockData =
                                GSON.fromJson(rootObject.get("Global Quote"), dataType);
                StockQuote stock = newStockQuote(stockData);
                return new StockQuotesResponse(stock);
            } catch (JsonSyntaxException e) {
                throw new AlphaVantageException("StockQuotes api change", e);
            }
        }

        private StockQuote newStockQuote(final Map<String, String> stockData) {
            String value = stockData.get("02. open");
            return new StockQuote(stockData.get("01. symbol"), parseDouble(value),
                            parseDouble(stockData.get("03. high")),
                            parseDouble(stockData.get("04. low")),
                            parseDouble(stockData.get("05. price")), getVolume(stockData),
                            LocalDate.parse(stockData.get("07. latest trading day")),
                            parseDouble(stockData.get("08. previous close")),
                            parseDouble(stockData.get("09. change")),
                            getChangePercent(stockData.get("10. change percent")));
        }

        private long getVolume(final Map<String, String> values) {
            try {
                return Long.parseLong(values.get("06. volume"));
            } catch (NumberFormatException e) {
                return 0L;
            }
        }
    }

    private static double getChangePercent(final String changePercentStr) {
        if (changePercentStr == null || changePercentStr.trim().isEmpty())
            return 0;

        return parseDouble(changePercentStr.substring(0, changePercentStr.length() - 1));
    }

    private static double parseDouble(final String value) {
        if (value == null || value.trim().isEmpty())
            return 0;

        return Double.parseDouble(value);
    }
}
