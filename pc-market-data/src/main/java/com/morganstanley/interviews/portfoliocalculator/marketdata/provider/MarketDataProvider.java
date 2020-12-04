package com.morganstanley.interviews.portfoliocalculator.marketdata.provider;

import com.morganstanley.interviews.portfoliocalculator.marketdata.model.MarketData;

public interface MarketDataProvider {
    public void publish(MarketData marketData);

    public MarketData consume() throws InterruptedException;

}
