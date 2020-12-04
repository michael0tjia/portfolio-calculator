package com.morganstanley.interviews.portfoliocalculator.marketdata;

import com.morganstanley.interviews.portfoliocalculator.cache.Cache;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheManager;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheName;
import com.morganstanley.interviews.portfoliocalculator.marketdata.model.MarketData;
import com.morganstanley.interviews.portfoliocalculator.marketdata.provider.PriceConsumer;
import com.morganstanley.interviews.portfoliocalculator.marketdata.provider.PriceConsumerImpl;
import com.morganstanley.interviews.portfoliocalculator.marketdata.provider.PriceProvider;
import com.morganstanley.interviews.portfoliocalculator.marketdata.provider.PriceProviderImpl;
import com.morganstanley.interviews.portfoliocalculator.model.CommonStock;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

public class MockMarketDataService implements MarketDataService {
    private final LinkedBlockingQueue<MarketData> marketDataQueue = new LinkedBlockingQueue<>();
    private final Cache<CommonStock> commonStockCache;
    private final ScheduledExecutorService scheduledExecutorService;
    private final Map<String, Consumer<MarketData>> tickerConsumerMap = new ConcurrentHashMap<>();
    List<CommonStock> commonStocks;

    public MockMarketDataService(final CacheManager cacheManager) {
        this.commonStockCache = cacheManager.getCache(CacheName.CommonStock);
        this.commonStocks = new ArrayList<>(this.commonStockCache.getAllRecords());
        this.scheduledExecutorService = Executors.newScheduledThreadPool(this.commonStocks.size());
    }

    @PostConstruct
    public void init() {
        for (final CommonStock commonStock : this.commonStocks) {
            final PriceProvider priceProvider = new PriceProviderImpl(this.scheduledExecutorService, this.marketDataQueue, commonStock);
            priceProvider.run();
        }

        final PriceConsumer priceConsumer = new PriceConsumerImpl(this.marketDataQueue, this::onMarketData);
        final Thread thread = new Thread(priceConsumer);
        thread.start();
    }

    @Override
    public void subscribe(final String ticker, final Consumer<MarketData> marketDataConsumer) {
        this.tickerConsumerMap.put(ticker, marketDataConsumer);
    }

    @Override
    public void unSubscribe(final String ticker) {
        this.tickerConsumerMap.remove(ticker);
    }

    private void onMarketData(final MarketData marketData) {
        final Consumer<MarketData> marketDataConsumer = this.tickerConsumerMap.get(marketData.getTicker());
        if (marketDataConsumer != null) {
            marketDataConsumer.accept(marketData);
        }
    }
}
