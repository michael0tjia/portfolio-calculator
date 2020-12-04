package com.morganstanley.interviews.portfoliocalculator.marketdata.model;

public class MarketDataImpl implements MarketData {
    private final String ticker;
    private final double price;

    public MarketDataImpl(final String ticker, final double price) {
        this.ticker = ticker;
        this.price = price;
    }

    @Override
    public String getTicker() {
        return this.ticker;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "MarketDataImpl{" +
                "ticker='" + this.ticker + '\'' +
                ", price=" + this.price +
                '}';
    }
}
