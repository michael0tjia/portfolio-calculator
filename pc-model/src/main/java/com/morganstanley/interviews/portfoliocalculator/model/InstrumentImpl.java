package com.morganstanley.interviews.portfoliocalculator.model;

import java.util.Objects;

public class InstrumentImpl implements Instrument {
    private final String ticker;
    private final InstrumentType instrumentType;

    public InstrumentImpl(final String ticker, final InstrumentType instrumentType) {
        this.ticker = ticker;
        this.instrumentType = instrumentType;
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
    public InstrumentType getInstrumentType() {
        return this.instrumentType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final InstrumentImpl that = (InstrumentImpl) o;
        return Objects.equals(this.ticker, that.ticker) &&
                this.instrumentType == that.instrumentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ticker, this.instrumentType);
    }

    @Override
    public String toString() {
        return "InstrumentImpl{" +
                "ticker='" + this.ticker + '\'' +
                ", instrumentType=" + this.instrumentType +
                '}';
    }
}
