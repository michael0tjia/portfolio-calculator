package com.morganstanley.interviews.portfoliocalculator.marketdata;

import com.morganstanley.interviews.portfoliocalculator.marketdata.model.MarketData;

import java.util.function.Consumer;

public interface MarketDataService {
    public void subscribe(String ticker, Consumer<MarketData> marketDataConsumer);

    public void unSubscribe(String ticker);
}
