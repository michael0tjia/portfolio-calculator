package com.morganstanley.interviews.portfoliocalculator.model;

import java.util.Objects;

public class CommonStockImpl implements CommonStock {
    private final String ticker;
    private double price;
    private double expectedReturn;
    private double volatility;

    public CommonStockImpl(final String ticker) {
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
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(final double price) {
        this.price = price;
    }

    @Override
    public double getExpectedReturn() {
        return this.expectedReturn;
    }

    @Override
    public void setExpectedReturn(final double expectedReturn) {
        this.expectedReturn = expectedReturn;
    }

    @Override
    public double getVolatility() {
        return this.volatility;
    }

    @Override
    public void setVolatility(final double volatility) {
        this.volatility = volatility;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CommonStockImpl that = (CommonStockImpl) o;
        return Double.compare(that.price, this.price) == 0 &&
                Double.compare(that.expectedReturn, this.expectedReturn) == 0 &&
                Double.compare(that.volatility, this.volatility) == 0 &&
                Objects.equals(this.ticker, that.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ticker, this.price, this.expectedReturn, this.volatility);
    }

    @Override
    public String toString() {
        return "CommonStockImpl{" +
                "ticker='" + this.ticker + '\'' +
                ", price=" + this.price +
                ", expectedReturn=" + this.expectedReturn +
                ", volatility=" + this.volatility +
                '}';
    }
}
