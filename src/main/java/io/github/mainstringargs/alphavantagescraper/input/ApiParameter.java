package io.github.mainstringargs.alphavantagescraper.input;

/**
 * Parameters for api call. Ex: interval=15min&amp;outputsize=full; 'interval' is key, and '15min'
 * is value.
 */
public interface ApiParameter {

    /**
     * Get key for parameter.
     *
     * @return the key
     */
    String getKey();

    /**
     * Get value for parameter.
     *
     * @return the value
     */
    String getValue();
}
