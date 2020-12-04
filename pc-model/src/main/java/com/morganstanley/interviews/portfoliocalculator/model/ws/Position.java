package com.morganstanley.interviews.portfoliocalculator.model.ws;

import java.util.Objects;

public class Position {
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return Double.compare(position.price, this.price) == 0 &&
                this.quantity == position.quantity &&
                Double.compare(position.marketValue, this.marketValue) == 0 &&
                Objects.equals(this.ticker, position.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ticker, this.price, this.quantity, this.marketValue);
    }

    @Override
    public String toString() {
        return "Position{" +
                "ticker='" + this.ticker + '\'' +
                ", price=" + this.price +
                ", quantity=" + this.quantity +
                ", marketValue=" + this.marketValue +
                '}';
    }
}
