package io.github.mainstringargs.alphavantagescraper.examples;

import java.util.List;
import java.util.Map;
import io.github.mainstringargs.alphavantagescraper.AlphaVantageConnector;
import io.github.mainstringargs.alphavantagescraper.TimeSeries;
import io.github.mainstringargs.alphavantagescraper.input.timeseries.Interval;
import io.github.mainstringargs.alphavantagescraper.input.timeseries.OutputSize;
import io.github.mainstringargs.alphavantagescraper.output.AlphaVantageException;
import io.github.mainstringargs.alphavantagescraper.output.timeseries.IntraDay;
import io.github.mainstringargs.alphavantagescraper.output.timeseries.data.StockData;
import io.github.mainstringargs.alphavantagescraper.properties.AlphaVantageAPIKey;

public class AlphaVantageTimeSeriesExample {
    public static void main(String[] args) {
        String apiKey = AlphaVantageAPIKey.getAPIKey();
        int timeout = 3000;
        AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
        TimeSeries stockTimeSeries = new TimeSeries(apiConnector);

        try {
            IntraDay response =
                            stockTimeSeries.intraDay("SQQQ", Interval.ONE_MIN, OutputSize.COMPACT);
            Map<String, String> metaData = response.getMetaData();
            System.out.println("Information: " + metaData.get("1. Information"));
            System.out.println("Stock: " + metaData.get("2. Symbol"));

            List<StockData> stockData = response.getStockData();
            stockData.forEach(stock -> {
                System.out.println("date:   " + stock.getDateTime());
                System.out.println("open:   " + stock.getOpen());
                System.out.println("high:   " + stock.getHigh());
                System.out.println("low:    " + stock.getLow());
                System.out.println("close:  " + stock.getClose());
                System.out.println("volume: " + stock.getVolume());
            });
        } catch (AlphaVantageException e) {
            System.out.println("something went wrong");
        }
    }
}
