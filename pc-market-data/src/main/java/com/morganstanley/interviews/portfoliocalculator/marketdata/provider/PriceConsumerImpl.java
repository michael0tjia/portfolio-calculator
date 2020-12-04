package com.morganstanley.interviews.portfoliocalculator.marketdata.provider;

import com.morganstanley.interviews.portfoliocalculator.marketdata.model.MarketData;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class PriceConsumerImpl implements PriceConsumer {
    private final LinkedBlockingQueue<MarketData> marketDataQueue;
    private final Consumer<MarketData> onMarketData;

    public PriceConsumerImpl(final LinkedBlockingQueue<MarketData> marketDataQueue, final Consumer<MarketData> onMarketData) {
        this.marketDataQueue = marketDataQueue;
        this.onMarketData = onMarketData;
    }

    @Override
    public void run() {
        while (true) {
            try {
                final MarketData marketData = this.marketDataQueue.take();
                this.onMarketData.accept(marketData);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}
