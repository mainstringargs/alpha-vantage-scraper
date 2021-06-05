package io.github.mainstringargs.alphaVantageScraper;

// TODO: Auto-generated Javadoc
/**
 * The Class AlphaVantageAPIKey.
 */
public class AlphaVantageAPIKey {

    /**
     * Gets the API key.
     *
     * @return the API key
     */
    public static String getAPIKey() {
        return AlphaVantageScraperProperties.getProperty("alpha-vantage-api-key", "");
    }
}
