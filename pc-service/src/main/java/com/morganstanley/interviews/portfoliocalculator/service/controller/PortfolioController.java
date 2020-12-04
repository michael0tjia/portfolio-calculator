package com.morganstanley.interviews.portfoliocalculator.service.controller;

import com.morganstanley.interviews.portfoliocalculator.core.service.PortfolioService;
import com.morganstanley.interviews.portfoliocalculator.model.ws.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Portfolio getPortfolioMarketValue() {
        final Portfolio portfolio = this.portfolioService.createPortfolio();
        return portfolio;
    }
}
