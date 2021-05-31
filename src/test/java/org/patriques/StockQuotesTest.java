package org.patriques;

import org.junit.Test;
import org.patriques.output.quote.StockQuotesResponse;
import org.patriques.output.quote.data.StockQuote;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StockQuotesTest {

  @Test
  public void singleStockQuote() {
    String json = "" +
            "{\n" +
            "    \"Global Quote\": {\n" +
            "            \"01. symbol\": \"MSFT\",\n" +
            "            \"02. open\": \"250.9950\",\n" +
            "            \"03. high\": \"252.0800\",\n" +
            "            \"04. low\": \"249.5600\",\n" +
            "            \"05. price\": \"96.3850\",\n" +
            "            \"06. volume\": \"987654321\",\n" +
            "            \"07. latest trading day\": \"2018-05-18\",\n" +
            "            \"08. previous close\": \"98.3850\",\n" +
            "            \"09. change\": \"0.3700\",\n" +
            "            \"10. change percent\": \"0.1484%\"\n" +
            "        }\n" +
            "}";
    StockQuotes stockQuotes = new StockQuotes(parameters -> json);
    StockQuotesResponse resp = stockQuotes.quote("DUMMY");

    StockQuote stockQuote = resp.getStockQuote();
    assertThat(stockQuote.getSymbol(), is(equalTo("MSFT")));
    assertThat(stockQuote.getOpen(), is(equalTo(250.9950)));
    assertThat(stockQuote.getHigh(), is(equalTo(252.0800)));
    assertThat(stockQuote.getLow(), is(equalTo(249.5600)));
    assertThat(stockQuote.getPrice(), is(equalTo(96.3850)));
    assertThat(stockQuote.getVolume(), is(equalTo(987654321L)));
    assertThat(stockQuote.getLatestTradingDay(), is(equalTo(LocalDate.of(2018, 05, 18))));
    assertThat(stockQuote.getPreviousClose(), is(equalTo(98.3850)));
    assertThat(stockQuote.getChange(), is(equalTo(0.3700)));
    assertThat(stockQuote.getChangePercent(), is(equalTo(0.1484)));
  }

  @Test
  public void stockNoVolume() {
    String json = "" +
            "{\n" +
            "    \"Global Quote\": {\n" +
            "            \"01. symbol\": \"MSFT\",\n" +
            "            \"02. price\": \"96.3850\",\n" +
            "            \"06. volume\": \"--\",\n" +
            "            \"07. latest trading day\": \"2018-05-18\"\n" +
            "        }\n" +
            "}";
    StockQuotes stockQuotes = new StockQuotes(parameters -> json);
    StockQuotesResponse resp = stockQuotes.quote("DUMMY");

    StockQuote stockQuote = resp.getStockQuote();
    assertThat(stockQuote.getVolume(), is(equalTo(0L)));
  }
}
