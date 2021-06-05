package io.github.mainstringargs.alphaVantageScraper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class AlphaVantageScraperProperties.
 */
public class AlphaVantageScraperProperties {

    /** The property file. */
    private static Properties propertyFile;

    static {
        InputStream is = AlphaVantageScraperProperties.class
                        .getResourceAsStream("/alpha-vantage-scraper.properties");
        propertyFile = new Properties();
        try {
            propertyFile.load(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Gets the property.
     *
     * @param key the key
     * @param defaultValue the default value
     * @return the property
     */
    public static String getProperty(String key, String defaultValue) {
        return propertyFile.getProperty(key, defaultValue);
    }

}
