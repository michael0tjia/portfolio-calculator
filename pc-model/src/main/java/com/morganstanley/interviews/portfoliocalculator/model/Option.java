package com.morganstanley.interviews.portfoliocalculator.model;

public interface Option extends Cacheable {
    String getTicker();

    String getUnderlygingTicker();

    OptionRight getOptionRight();

    double getTimeToMaturity();

    void setTimeToMaturity(final double timeToMaturity);

    double getStrikePrice();

    void setStrikePrice(final double strikePrice);
}
