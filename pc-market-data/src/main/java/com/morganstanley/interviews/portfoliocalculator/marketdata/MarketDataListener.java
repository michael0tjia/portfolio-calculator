package com.morganstanley.interviews.portfoliocalculator.marketdata;

import com.morganstanley.interviews.portfoliocalculator.marketdata.model.MarketData;

public interface MarketDataListener {
    void handle(MarketData marketData);
}
