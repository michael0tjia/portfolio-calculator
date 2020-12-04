package com.morganstanley.interviews.portfoliocalculator.websocketclient.model;

public class Position {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private String ticker;
    private double price;
    private long quantity;
    private double marketValue;

    public Position() {
    }

    public String getTicker() {
        return this.ticker;
    }

    public void setTicker(final String ticker) {
        this.ticker = ticker;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final long quantity) {
        this.quantity = quantity;
    }

    public double getMarketValue() {
        return this.marketValue;
    }

    public void setMarketValue(final double marketValue) {
        this.marketValue = marketValue;
    }

    @Override
    public String toString() {
        return "Position{" +
                "ticker='" + this.ticker + '\'' +
                ", price=" + this.price +
                ", quantity=" + this.quantity +
                ", marketValue=" + this.marketValue +
                '}' + LINE_SEPARATOR;
    }
}
