package io.github.mainstringargs.alphaVantageScraper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import io.github.mainstringargs.AlphaVantageConnector;
import io.github.mainstringargs.StockQuotes;
import io.github.mainstringargs.output.quote.StockQuotesResponse;
import io.github.mainstringargs.output.quote.data.StockQuote;

/**
 * The Class RobinhoodFinanceService.
 */
public class AlphaVantageFinanceService
                implements io.github.mainstringargs.stockData.spi.StockDataService {


    String apiKey = AlphaVantageAPIKey.getAPIKey();
    int timeout = 3000;
    AlphaVantageConnector apiConnector;
    StockQuotes sqs;


    private boolean isInitialized = false;

    /*
     * (non-Javadoc)
     * 
     * @see io.github.mainstringargs.stockData.spi.StockDataService#init()
     */
    public void init() {

        if (!isInitialized) {
            apiConnector = new AlphaVantageConnector(apiKey, timeout);
            sqs = new StockQuotes(apiConnector);
            isInitialized = true;
        }


    }

    /*
     * (non-Javadoc)
     * 
     * @see io.github.mainstringargs.stockData.spi.StockDataService#getServiceName()
     */
    public String getServiceName() {
        return "AlphaVantage";
    }

    /*
     * (non-Javadoc)
     * 
     * @see io.github.mainstringargs.stockData.spi.StockDataService#getShortServiceName()
     */
    @Override
    public String getShortServiceName() {
        return "AV";
    }

    /*
     * (non-Javadoc)
     * 
     * @see io.github.mainstringargs.stockData.spi.StockDataService#getStockData(java.lang.String)
     */
    @Override
    public Map<String, Object> getStockData(String ticker) {
        return getAlphaVantageData(ticker);
    }

    /*
     * (non-Javadoc)
     * 
     * @see io.github.mainstringargs.stockData.spi.StockDataService#getStockData(java.lang.String[])
     */
    public Map<String, Map<String, Object>> getStockData(String... tickers) {

        Map<String, Map<String, Object>> allStockData = new HashMap<String, Map<String, Object>>();

        for (String symbol : tickers) {

            Map<String, Object> stockData = getAlphaVantageData(symbol);

            allStockData.put(symbol.toUpperCase(), stockData);

        }

        return allStockData;

    }


    private Map<String, Object> getAlphaVantageData(String symbol) {

        if (!isInitialized) {
            init();
        }

        Map<String, Object> stockData = new LinkedHashMap<String, Object>();

        StockQuotesResponse symbolData = sqs.quote(symbol);

        stockData.put("Symbol", symbol);

        StockQuote sqs = symbolData.getStockQuote();

        if (sqs != null) {
            stockData.put("Open", sqs.getOpen());
            stockData.put("High", sqs.getHigh());
            stockData.put("Low", sqs.getLow());
            stockData.put("Price", sqs.getPrice());
            stockData.put("Volume", sqs.getVolume());
            stockData.put("Latest trading day", sqs.getLatestTradingDay());
            stockData.put("Previous close", sqs.getPreviousClose());
            stockData.put("Change", sqs.getChange());
            stockData.put("Change percent", sqs.getChangePercent());
        }

        return stockData;
    }



    // public Map<String, Map<String, Object>> getStockData() {
    // return null;
    //
    // }
}
