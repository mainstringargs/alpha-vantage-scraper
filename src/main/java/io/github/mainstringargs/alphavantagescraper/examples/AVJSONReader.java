package io.github.mainstringargs.alphavantagescraper.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import io.github.mainstringargs.alphavantagescraper.output.timeseries.data.StockData;

public class AVJSONReader {

    private static final DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss");;

    public static void main(String[] args) {


        List<StockData> stockData = getStockDataForDate("TQQQ", LocalDate.of(2018, 11, 29));

        for (StockData sd : stockData) {
            System.out.println(sd.getDateTime() + " " + sd.getOpen() + " " + sd.getLow() + " "
                            + sd.getHigh() + " " + sd.getClose() + " " + sd.getAdjustedClose());
        }
    }

    public static List<StockData> getStockDataForDate(String ticker, LocalDate ldt) {

        File directory = new File("stockHistoryData");

        if (directory.exists()) {

            File[] filesInDir = directory.listFiles();

            for (File file : filesInDir) {

                String fileName = file.getName();

                String[] splitFileName = fileName.replaceAll(".json", "").split("-");

                System.out.println(Arrays.toString(splitFileName));

                String fnTicker = splitFileName[0];
                String startTimeRange = splitFileName[1];
                String endTimeRange = splitFileName[2];

                LocalDate startDateTime = LocalDate.parse(startTimeRange, formatter);
                LocalDate endDateTime = LocalDate.parse(endTimeRange, formatter);



                if (fnTicker.equals(ticker)
                                && (startDateTime.isBefore(ldt) || startDateTime.equals(ldt))
                                && ((endDateTime.isAfter(ldt) || endDateTime.equals(ldt)))) {

                    List<StockData> stockData = loadStockDataFromJSON(file);

                    return stockData.stream()
                                    .filter(sd -> sd.getDateTime().toLocalDate().equals(ldt))
                                    .collect(Collectors.<StockData>toList());

                }

            }

        }


        return new ArrayList<StockData>();
    }

    private static List<StockData> loadStockDataFromJSON(File file) {


        JsonReader jsonReader = null;
        try {
            jsonReader = new JsonReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Type listType = new TypeToken<List<StockData>>() {}.getType();

        List<StockData> jsonList = new Gson().fromJson(jsonReader, listType);

        return jsonList;
    }


}
