package io.github.mainstringargs.alphaVantageScraper;

import io.github.mainstringargs.AlphaVantageConnector;
import io.github.mainstringargs.StockQuotes;

public class AlphaVantageTechnicalIndicatorsExample {
    public static void main(String[] args) {
        String apiKey = AlphaVantageAPIKey.getAPIKey();
        int timeout = 3000;
        AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);

        StockQuotes qs = new StockQuotes(apiConnector);

        // System.out.println(qs.quote("AMZN").getStockQuotes().get(0).getPrice());

        // try {
        // TechnicalIndicators ti = new TechnicalIndicators(apiConnector);
        //
        // RSI rsi = ti.rsi("FE", Interval.ONE_MIN, TimePeriod.of(15), SeriesType.CLOSE);
        //
        // System.out.println(rsi.getMetaData());
        //
        // System.out.println("Count " + rsi.getData().size());
        //
        // for (IndicatorData data : rsi.getData()) {
        // System.out.println(data.getDateTime() + " " + data.getData());
        // }
        //
        // } catch (AlphaVantageException e) {
        // System.out.println("something went wrong");
        // }
    }
}
