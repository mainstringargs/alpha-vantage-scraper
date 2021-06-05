package io.github.mainstringargs.alphaVantageScraper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.mainstringargs.AlphaVantageConnector;
import io.github.mainstringargs.TimeSeries;
import io.github.mainstringargs.input.timeseries.Interval;
import io.github.mainstringargs.input.timeseries.OutputSize;
import io.github.mainstringargs.output.AlphaVantageException;
import io.github.mainstringargs.output.timeseries.IntraDay;
import io.github.mainstringargs.output.timeseries.data.StockData;

public class AVJSONWriter {

    public static void main(String[] args) {

        String apiKey = AlphaVantageAPIKey.getAPIKey();
        int timeout = 3000;
        AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
        TimeSeries stockTimeSeries = new TimeSeries(apiConnector);


        writeDataToJSON(stockTimeSeries, "SQQQ");
        writeDataToJSON(stockTimeSeries, "TQQQ");
    }

    private static void writeDataToJSON(TimeSeries stockTimeSeries, String ticker) {

        IntraDay response = null;
        try {
            response = stockTimeSeries.intraDay("SQQQ", Interval.ONE_MIN, OutputSize.FULL);
            Map<String, String> metaData = response.getMetaData();
            System.out.println("Information: " + metaData.get("1. Information"));
            System.out.println("Stock: " + metaData.get("2. Symbol"));

        } catch (AlphaVantageException e) {
            System.out.println("something went wrong");
        }

        List<StockData> stockData = response.getStockData();

        Collections.reverse(stockData);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss");;


        String startDate = stockData.get(0).getDateTime().format(formatter);

        String endDate = stockData.get(stockData.size() - 1).getDateTime().format(formatter);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // convert the Java object to json
        try {
            String jsonString = gson.toJson(stockData);

            File folder = new File("stockHistoryData");

            if (!folder.exists()) {
                folder.mkdir();
            }

            File file = new File("stockHistoryData/" + ticker + "-" + startDate + "-" + endDate
                            + ".json");

            System.out.println("Writing.... " + file.getAbsolutePath());

            // Write JSON String to file
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
