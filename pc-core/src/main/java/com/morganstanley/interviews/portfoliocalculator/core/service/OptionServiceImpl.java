package com.morganstanley.interviews.portfoliocalculator.core.service;

import com.morganstanley.interviews.portfoliocalculator.cache.Cache;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheManager;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheName;
import com.morganstanley.interviews.portfoliocalculator.core.math.BlackScholes;
import com.morganstanley.interviews.portfoliocalculator.model.CommonStock;
import com.morganstanley.interviews.portfoliocalculator.model.Option;

public class OptionServiceImpl implements OptionService {
    private static final double RISK_FREE_RATE = 0.02;
    private final Cache<CommonStock> commonStockCache;

    public OptionServiceImpl(final CacheManager cacheManager) {
        this.commonStockCache = cacheManager.getCache(CacheName.CommonStock);
    }

    @Override
    public double getOptionPrice(final Option option) {
        final CommonStock commonStock = this.commonStockCache.getRecord(option.getUnderlygingTicker());
        double price = 0;
        switch (option.getOptionRight()) {
            case CALL:
                price = BlackScholes.callPrice(commonStock.getPrice(), option.getStrikePrice(), commonStock
                        .getVolatility(), option.getTimeToMaturity(), RISK_FREE_RATE);
                break;
            case PUT:
                price = BlackScholes.putPrice(commonStock.getPrice(), option.getStrikePrice(), commonStock
                        .getVolatility(), option.getTimeToMaturity(), RISK_FREE_RATE);
                break;
        }
        return price;
    }
}
