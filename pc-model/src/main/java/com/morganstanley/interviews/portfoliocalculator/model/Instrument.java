package com.morganstanley.interviews.portfoliocalculator.model;

public interface Instrument extends Cacheable {
    String getTicker();

    InstrumentType getInstrumentType();
}
