package com.morganstanley.interviews.portfoliocalculator.marketdata.math;

import java.util.Random;

public class RandomUtil {
    public static double getRandomDouble(final Random random, final double min, final double max) {
        return min + random.nextDouble() * (max - min);
    }
}
