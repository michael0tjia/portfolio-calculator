package com.morganstanley.interviews.portfoliocalculator.service;

import com.morganstanley.interviews.portfoliocalculator.cache.Cache;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheManager;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheName;
import com.morganstanley.interviews.portfoliocalculator.core.service.OptionService;
import com.morganstanley.interviews.portfoliocalculator.core.service.PortfolioService;
import com.morganstanley.interviews.portfoliocalculator.marketdata.MarketDataService;
import com.morganstanley.interviews.portfoliocalculator.marketdata.model.MarketData;
import com.morganstanley.interviews.portfoliocalculator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.annotation.PostConstruct;

public class PortfolioManagerImpl implements PortfolioManager {
    private final CacheManager cacheManager;
    private final Cache<Instrument> instrumentCache;
    private final Cache<CommonStock> commonStockCache;
    private final Cache<Option> optionCache;
    private final Cache<Position> positionCache;
    private final MarketDataService marketDataService;
    private final OptionService optionService;
    private final PortfolioService portfolioService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public PortfolioManagerImpl(final CacheManager cacheManager, final MarketDataService marketDataService,
                                final OptionService optionService, final PortfolioService portfolioService) {
        this.cacheManager = cacheManager;
        this.instrumentCache = cacheManager.getCache(CacheName.Instrument);
        this.commonStockCache = cacheManager.getCache(CacheName.CommonStock);
        this.optionCache = cacheManager.getCache(CacheName.Option);
        this.positionCache = cacheManager.getCache(CacheName.Position);
        this.marketDataService = marketDataService;
        this.optionService = optionService;
        this.portfolioService = portfolioService;
    }

    @PostConstruct
    public void init() {
        for (final Position position : this.positionCache.getAllRecords()) {
            final Instrument instrument = this.instrumentCache.getRecord(position.getTicker());
            if (instrument.getInstrumentType() == InstrumentType.COMMON_STOCK) {
                this.marketDataService.subscribe(instrument.getTicker(), this::onMarketData);
            } else if (instrument.getInstrumentType() == InstrumentType.OPTION) {
                final Option option = this.optionCache.getRecord(instrument.getTicker());
                this.marketDataService.subscribe(option.getUnderlygingTicker(), this::onMarketData);
            }
        }
    }

    private void onMarketData(final MarketData marketData) {
        final boolean hasUpdate = updatePortfolio(marketData);
        if (hasUpdate) {
            this.simpMessagingTemplate.convertAndSend("/topic/nav", String.valueOf(this.portfolioService.getNav()));
        }
    }

    private boolean updatePortfolio(final MarketData marketData) {
        boolean hasUpdate = false;
        final Position commonStockPosition = this.positionCache.getRecord(marketData.getTicker());
        if (commonStockPosition != null) {
            commonStockPosition.setPrice(marketData.getPrice());
            commonStockPosition.setMarketValue(commonStockPosition.getPrice() * commonStockPosition.getQuantity());
            hasUpdate = true;
        }

        for (final Position position : this.positionCache.getAllRecords()) {
            final Instrument instrument = this.instrumentCache.getRecord(position.getTicker());
            if (instrument.getInstrumentType() == InstrumentType.OPTION) {
                final Option option = this.optionCache.getRecord(instrument.getTicker());
                if (option.getUnderlygingTicker().equals(marketData.getTicker())) {
                    final double price = this.optionService.getOptionPrice(option);
                    position.setPrice(price);
                    position.setMarketValue(position.getPrice() * position.getQuantity());
                    hasUpdate = true;
                }
            }
        }

        return hasUpdate;
    }
}
