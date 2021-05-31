package org.patriques.output.quote.data;

import java.time.LocalDate;

/**
 * Representation of json object, i.e:
 * {
 *    "01. symbol": "IBM",
 *    "02. open": "144.2100",
 *    "03. high": "144.3300",
 *    "04. low": "143.4850",
 *    "05. price": "143.7400",
 *    "06. volume": "2534811",
 *    "07. latest trading day": "2021-05-28",
 *    "08. previous close": "143.8200",
 *    "09. change": "-0.0800",
 *    "10. change percent": "-0.0556%"
 * }
 */
public class StockQuote {
  private final String symbol;
  private final double open;
  private final double high;
  private final double low;
  private final double price;
  private final long volume;
  private final LocalDate latestTradingDay;
  private final double previousClose;
  private final double change;
  private final double changePercent;

  public StockQuote(String symbol, double open, double high, double low, double price, long volume, LocalDate latestTradingDay, double previousClose, double change, double changePercent) {
    this.symbol = symbol;
    this.open = open;
    this.high = high;
    this.low = low;
    this.price = price;
    this.volume = volume;
    this.latestTradingDay = latestTradingDay;
    this.previousClose = previousClose;
    this.change = change;
    this.changePercent = changePercent;
  }

  public String getSymbol() {
    return symbol;
  }

  public double getPrice() {
    return price;
  }

  public long getVolume() {
    return volume;
  }

  public double getOpen() {
    return open;
  }

  public double getHigh() {
    return high;
  }

  public double getLow() {
    return low;
  }

  public LocalDate getLatestTradingDay() {
    return latestTradingDay;
  }

  public double getPreviousClose() {
    return previousClose;
  }

  public double getChange() {
    return change;
  }

  public double getChangePercent() {
    return changePercent;
  }
}
