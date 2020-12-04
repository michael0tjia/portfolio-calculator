package com.morganstanley.interviews.portfoliocalculator.model;

public interface Position extends Cacheable {
    String getTicker();

    long getQuantity();

    void setQuantity(long quantity);

    double getPrice();

    void setPrice(double price);

    double getMarketValue();

    void setMarketValue(double marketValue);
}
