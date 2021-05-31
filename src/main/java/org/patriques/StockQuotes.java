package org.patriques;

import org.patriques.input.Function;
import org.patriques.input.Symbols;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.quote.StockQuotesResponse;

/**
 * The Batch Stock Quotes api provides stock quotes give a list of stock symbols.
 */
public class StockQuotes {

  private final ApiConnector apiConnector;

  /**
   * Constructs a Batch Stock Quotes api endpoint with the help of an {@link ApiConnector}
   *
   * @param apiConnector the connection to the api
   */
  public StockQuotes(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  /**
   * This API returns stock quotes updated realtime.
   *
   * @param symbols the stock symbols to lookup
   * @return {@link StockQuotesResponse} stock quote data
   */
  public StockQuotesResponse quote(String symbol) {
    String json = apiConnector.getRequest(new Symbols(symbol), Function.GLOBAL_QUOTE);
    return StockQuotesResponse.from(json);
  }
}
