package com.morganstanley.interviews.portfoliocalculator.core.service;

import com.morganstanley.interviews.portfoliocalculator.model.Position;
import com.morganstanley.interviews.portfoliocalculator.model.ws.Portfolio;

import java.util.List;

public interface PortfolioService {
    Portfolio createPortfolio();

    Portfolio createPortfolio(List<Position> positions);

    double getNav();

    double getNav(final List<Position> positions);
}
