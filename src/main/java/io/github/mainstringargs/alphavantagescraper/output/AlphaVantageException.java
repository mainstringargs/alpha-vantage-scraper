package io.github.mainstringargs.alphavantagescraper.output;

public class AlphaVantageException extends RuntimeException {
    public AlphaVantageException(String message, Exception e) {
        super(message, e);
    }

    public AlphaVantageException(String message) {
        super(message);
    }
}
