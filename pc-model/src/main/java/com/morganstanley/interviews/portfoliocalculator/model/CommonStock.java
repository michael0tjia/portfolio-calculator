package com.morganstanley.interviews.portfoliocalculator.model;

public interface CommonStock extends Cacheable {
    String getTicker();

    double getPrice();

    void setPrice(double price);

    double getExpectedReturn();

    void setExpectedReturn(final double expectedReturn);

    double getVolatility();

    void setVolatility(final double annualizedStandardDeviation);
}
