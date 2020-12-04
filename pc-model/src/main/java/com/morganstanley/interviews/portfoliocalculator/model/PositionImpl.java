package com.morganstanley.interviews.portfoliocalculator.model;

import java.util.Objects;

public class PositionImpl implements Position {
    private final String ticker;
    private long quantity;
    private double price;
    private double marketValue;

    public PositionImpl(final String ticker) {
        this.ticker = ticker;
    }

    @Override
    public Object getPrimaryKey() {
        return this.ticker;
    }

    @Override
    public String getTicker() {
        return this.ticker;
    }

    @Override
    public long getQuantity() {
        return this.quantity;
    }

    @Override
    public void setQuantity(final long quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(final double price) {
        this.price = price;
    }

    @Override
    public double getMarketValue() {
        return this.marketValue;
    }

    @Override
    public void setMarketValue(final double marketValue) {
        this.marketValue = marketValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PositionImpl portfolio = (PositionImpl) o;
        return this.quantity == portfolio.quantity &&
                Double.compare(portfolio.price, this.price) == 0 &&
                Double.compare(portfolio.marketValue, this.marketValue) == 0 &&
                Objects.equals(this.ticker, portfolio.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ticker, this.quantity, this.price, this.marketValue);
    }

    @Override
    public String toString() {
        return "PositionImpl{" +
                "ticker='" + this.ticker + '\'' +
                ", quantity=" + this.quantity +
                ", price=" + this.price +
                ", marketValue=" + this.marketValue +
                '}';
    }
}
