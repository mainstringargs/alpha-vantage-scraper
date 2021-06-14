package io.github.mainstringargs.alphavantagescraper;

import io.github.mainstringargs.alphavantagescraper.input.Function;
import io.github.mainstringargs.alphavantagescraper.input.Symbol;
import io.github.mainstringargs.alphavantagescraper.output.quote.StockQuotesResponse;

/**
 * The Stock Quotes api provides stock quotes give a list of stock symbols.
 */
public class StockQuotes {

    private final ApiConnector apiConnector;

    /**
     * Constructs a Stock Quotes api endpoint with the help of an {@link ApiConnector}
     *
     * @param apiConnector the connection to the api
     */
    public StockQuotes(ApiConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    /**
     * This API returns stock quotes updated realtime.
     *
     * @param symbol the stock symbol to lookup
     * @return {@link StockQuotesResponse} stock quote data
     */
    public StockQuotesResponse quote(String symbol) {
        String json = apiConnector.getRequest(new Symbol(symbol), Function.GLOBAL_QUOTE);
        return StockQuotesResponse.from(json);
    }
}
