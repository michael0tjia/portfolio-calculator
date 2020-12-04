package com.morganstanley.interviews.portfoliocalculator.core.service;

import com.morganstanley.interviews.portfoliocalculator.cache.Cache;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheManager;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheName;
import com.morganstanley.interviews.portfoliocalculator.model.Position;
import com.morganstanley.interviews.portfoliocalculator.model.ws.Portfolio;

import java.util.ArrayList;
import java.util.List;

public class PortfolioServiceImpl implements PortfolioService {
    private final Cache<Position> positionCache;

    public PortfolioServiceImpl(final CacheManager cacheManager) {
        this.positionCache = cacheManager.getCache(CacheName.Position);
    }

    @Override
    public Portfolio createPortfolio() {
        return createPortfolio(this.positionCache.getAllRecords());
    }

    @Override
    public Portfolio createPortfolio(final List<Position> positions) {
        final List<com.morganstanley.interviews.portfoliocalculator.model.ws.Position> wsPositions = new ArrayList<>();

        for (final Position position : positions) {
            final com.morganstanley.interviews.portfoliocalculator.model.ws.Position wsPosition = new com.morganstanley
                    .interviews.portfoliocalculator.model.ws.Position();
            wsPosition.setTicker(position.getTicker());
            wsPosition.setQuantity(position.getQuantity());
            wsPosition.setPrice(position.getPrice());
            wsPosition.setMarketValue(position.getMarketValue());
            wsPositions.add(wsPosition);
        }

        final Portfolio wsPortfolio = new Portfolio();
        wsPortfolio.setPositions(wsPositions);
        wsPortfolio.setNav(getNav(positions));

        return wsPortfolio;
    }

    @Override
    public double getNav() {
        return getNav(this.positionCache.getAllRecords());
    }

    @Override
    public double getNav(final List<Position> positions) {
        double nav = 0;
        for (final Position position : positions) {
            nav = nav + position.getMarketValue();
        }
        return nav;
    }
}
