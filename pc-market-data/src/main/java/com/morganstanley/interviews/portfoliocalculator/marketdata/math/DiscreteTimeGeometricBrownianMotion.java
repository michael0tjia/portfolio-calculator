package com.morganstanley.interviews.portfoliocalculator.marketdata.math;

import java.util.Random;

/**
 *
 */
public class DiscreteTimeGeometricBrownianMotion {
    private final Random random;

    public DiscreteTimeGeometricBrownianMotion(final Random random) {
        this.random = random;
    }

    /**
     * @param price
     * @param expectedReturn
     * @param sd
     * @param seconds
     * @return
     */
    public double newPrice(final double price, final double expectedReturn, final double sd, final double seconds) {
        final double delta = price * ((expectedReturn * seconds / 7257600.0) + (sd * this.random.nextGaussian() * Math.sqrt(seconds / 7257600.0)));
        return Math.max(price + delta, 0.0);
    }
}
