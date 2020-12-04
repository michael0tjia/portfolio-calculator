package com.morganstanley.interviews.portfoliocalculator.model;

import java.util.Objects;

public class OptionImpl implements Option {
    private final String ticker;
    private final String underlygingTicker;
    private final OptionRight optionRight;
    private double timeToMaturity;
    private double strikePrice;

    public OptionImpl(final String ticker, final String underlygingTicker, final OptionRight optionRight) {
        this.ticker = ticker;
        this.underlygingTicker = underlygingTicker;
        this.optionRight = optionRight;
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
    public String getUnderlygingTicker() {
        return this.underlygingTicker;
    }

    @Override
    public OptionRight getOptionRight() {
        return this.optionRight;
    }

    @Override
    public double getTimeToMaturity() {
        return this.timeToMaturity;
    }

    @Override
    public void setTimeToMaturity(final double timeToMaturity) {
        this.timeToMaturity = timeToMaturity;
    }

    @Override
    public double getStrikePrice() {
        return this.strikePrice;
    }

    @Override
    public void setStrikePrice(final double strikePrice) {
        this.strikePrice = strikePrice;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OptionImpl option = (OptionImpl) o;
        return Double.compare(option.timeToMaturity, this.timeToMaturity) == 0 &&
                Double.compare(option.strikePrice, this.strikePrice) == 0 &&
                Objects.equals(this.ticker, option.ticker) &&
                Objects.equals(this.underlygingTicker, option.underlygingTicker) &&
                this.optionRight == option.optionRight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ticker, this.underlygingTicker, this.optionRight, this.timeToMaturity, this.strikePrice);
    }

    @Override
    public String toString() {
        return "OptionImpl{" +
                "ticker='" + this.ticker + '\'' +
                ", underlygingTicker='" + this.underlygingTicker + '\'' +
                ", optionRight=" + this.optionRight +
                ", timeToMaturity=" + this.timeToMaturity +
                ", strikePrice=" + this.strikePrice +
                '}';
    }
}
