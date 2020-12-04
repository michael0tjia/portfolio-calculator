package com.morganstanley.interviews.portfoliocalculator.core.math;

import org.apache.commons.math3.distribution.NormalDistribution;

public class BlackScholes {
    public static final NormalDistribution n = new NormalDistribution();

    public static Double callPrice(final double stockPrice, final double strikePrice, final double stockVolatility, final double timeToMaturity, final double riskFreeRate) {
        final double d1 = d1(stockPrice, strikePrice, stockVolatility, timeToMaturity, riskFreeRate);
        return stockPrice * n.cumulativeProbability(d1) - strikePrice * Math.exp(-riskFreeRate * timeToMaturity) * n.cumulativeProbability(d2(d1, stockVolatility, timeToMaturity));
    }

    public static Double putPrice(final double stockPrice, final double strikePrice, final double stockVolatility, final double timeToMaturity, final double riskFreeRate) {
        final double d1 = d1(stockPrice, strikePrice, stockVolatility, timeToMaturity, riskFreeRate);
        return strikePrice * Math.exp(-riskFreeRate * timeToMaturity) * n.cumulativeProbability(-d2(d1, stockVolatility, timeToMaturity)) - stockPrice * n.cumulativeProbability(-d1);
    }

    private static Double d1(final double stockPrice, final double strikePrice, final double stockVolatility, final double timeToMaturity, final double riskFreeRate) {
        return (Math.log(stockPrice / strikePrice) + (riskFreeRate + Math.pow(stockVolatility, 2) / 2) * timeToMaturity) / (stockVolatility * Math.sqrt(timeToMaturity));
    }

    private static Double d2(final double d1, final double stockVolatility, final double timeToMaturity) {
        return d1 - stockVolatility * Math.sqrt(timeToMaturity);
    }
}
