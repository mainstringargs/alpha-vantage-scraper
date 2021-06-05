package io.github.mainstringargs;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import io.github.mainstringargs.input.timeseries.OutputSize;
import io.github.mainstringargs.output.exchange.CurrencyExchange;
import io.github.mainstringargs.output.exchange.Daily;
import io.github.mainstringargs.output.exchange.data.CurrencyExchangeData;
import io.github.mainstringargs.output.exchange.data.ForexData;

public class CurrencyExchangeTest {
    private ForeignExchange foreignExchange;

    @Test
    public void conversionFromEURToUSD() {
        String json = "" + "{\n" + "    \"Realtime Currency Exchange Rate\": {\n"
                        + "        \"1. From_Currency Code\": \"BTC\",\n"
                        + "        \"2. From_Currency Name\": \"Bitcoin\",\n"
                        + "        \"3. To_Currency Code\": \"CNY\",\n"
                        + "        \"4. To_Currency Name\": \"Chinese Yuan\",\n"
                        + "        \"5. Exchange Rate\": \"115369.98857500\",\n"
                        + "        \"6. Last Refreshed\": \"2017-12-12 20:06:05\",\n"
                        + "        \"7. Time Zone\": \"UTC\"\n" + "    }\n" + "}";
        foreignExchange = new ForeignExchange(apiParameters -> json);
        CurrencyExchange exchange = foreignExchange.currencyExchangeRate("EUR", "USD");

        CurrencyExchangeData data = exchange.getData();
        assertThat(data.getFromCurrencyCode(), equalTo("BTC"));
        assertThat(data.getFromCurrencyName(), equalTo("Bitcoin"));
        assertThat(data.getToCurrencyCode(), equalTo("CNY"));
        assertThat(data.getToCurrencyName(), equalTo("Chinese Yuan"));
        assertThat(data.getExchangeRate(), equalTo(115369.98857500f));
        assertThat(data.getTime().toString(), equalTo("2017-12-12T20:06:05"));
        assertThat(data.getTimezone(), equalTo("UTC"));
    }

    @Test
    public void dailyForexEURToUSD() {
        String json = "" + "{\n" + "    \"Meta Data\": {\n"
                        + "        \"1. Information\": \"Forex Daily Prices (open, high, low, close)\",\n"
                        + "        \"2. From Symbol\": \"EUR\",\n"
                        + "        \"3. To Symbol\": \"USD\",\n"
                        + "        \"4. Output Size\": \"Compact\",\n"
                        + "        \"5. Last Refreshed\": \"2018-12-25 19:15:00\",\n"
                        + "        \"6. Time Zone\": \"GMT+8\"\n" + "    },\n"
                        + "    \"Time Series FX (Daily)\": {\n" + "        \"2018-12-25\": {\n"
                        + "            \"1. open\": \"1.1413\",\n"
                        + "            \"2. high\": \"1.1422\",\n"
                        + "            \"3. low\": \"1.1364\",\n"
                        + "            \"4. close\": \"1.1377\"\n" + "        }\n" + "    }\n"
                        + "}";
        foreignExchange = new ForeignExchange(apiParameters -> json);
        Daily dailyForex = foreignExchange.daily("EUR", "USD", OutputSize.COMPACT);

        Map<String, String> metaData = dailyForex.getMetaData();
        List<ForexData> data = dailyForex.getForexData();
        assertThat(metaData.get("2. From Symbol"), equalTo("EUR"));
        assertThat(metaData.get("3. To Symbol"), equalTo("USD"));
        assertThat(metaData.get("6. Time Zone"), equalTo("GMT+8"));
        assertThat(data.size(), equalTo(1));
        assertThat(data.get(0).getOpen(), equalTo(1.1413));
        assertThat(data.get(0).getClose(), equalTo(1.1377));
        assertThat(data.get(0).getLow(), equalTo(1.1364));
        assertThat(data.get(0).getHigh(), equalTo(1.1422));
        assertThat(data.get(0).getDateTime(), equalTo(LocalDateTime.of(2018, 12, 25, 0, 0)));
    }

}
