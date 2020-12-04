package com.morganstanley.interviews.portfoliocalculator.marketdata.provider;

import com.morganstanley.interviews.portfoliocalculator.marketdata.math.DiscreteTimeGeometricBrownianMotion;
import com.morganstanley.interviews.portfoliocalculator.marketdata.math.RandomUtil;
import com.morganstanley.interviews.portfoliocalculator.marketdata.model.MarketData;
import com.morganstanley.interviews.portfoliocalculator.marketdata.model.MarketDataImpl;
import com.morganstanley.interviews.portfoliocalculator.model.CommonStock;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PriceProviderImpl implements PriceProvider {
    private static final double MIN_DELTA_T = 0.5;
    private static final double MAX_DELTA_T = 2;
    private final CommonStock commonStock;
    private final LinkedBlockingQueue<MarketData> marketDataQueue;
    private final ScheduledExecutorService scheduledExecutorService;
    private final DiscreteTimeGeometricBrownianMotion discreteTimeGeometricBrownianMotion = new DiscreteTimeGeometricBrownianMotion(new Random());
    private final Random random = new Random();
    private double price;

    public PriceProviderImpl(final ScheduledExecutorService scheduledExecutorService,
                             final LinkedBlockingQueue<MarketData> marketDataQueue, final CommonStock commonStock) {
        this.scheduledExecutorService = scheduledExecutorService;
        this.marketDataQueue = marketDataQueue;
        this.commonStock = commonStock;
        this.price = commonStock.getPrice();
    }

    @Override
    public CommonStock getCommonStock() {
        return this.commonStock;
    }

    @Override
    public void run() {
        final MarketData marketData = new MarketDataImpl(this.commonStock.getTicker(), this.price);
        this.marketDataQueue.add(marketData);

        final double deltaT = RandomUtil.getRandomDouble(this.random, MIN_DELTA_T, MAX_DELTA_T);
        this.price = this.discreteTimeGeometricBrownianMotion.newPrice(this.price, this.commonStock.getExpectedReturn(),
                this.commonStock.getVolatility(), deltaT);

        final long periodInMicrosecond = Math.round(deltaT * 1000 * 1000);
        this.scheduledExecutorService.schedule(this, periodInMicrosecond, TimeUnit.MICROSECONDS);
    }
}
