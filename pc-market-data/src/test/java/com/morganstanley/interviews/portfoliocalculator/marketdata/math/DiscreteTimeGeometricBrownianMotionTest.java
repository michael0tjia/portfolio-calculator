package com.morganstanley.interviews.portfoliocalculator.marketdata.math;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscreteTimeGeometricBrownianMotionTest {
    DiscreteTimeGeometricBrownianMotion discreteTimeGeometricBrownianMotion;
    @Mock
    Random random;

    @Before
    public void setUp() throws Exception {
        this.discreteTimeGeometricBrownianMotion = new DiscreteTimeGeometricBrownianMotion(this.random);
    }

    @Test
    public void newPriceShouldNeverBeLessThanZero() throws Exception {
        when(this.random.nextGaussian()).thenReturn(-1d);
        final double newPrice = this.discreteTimeGeometricBrownianMotion.newPrice(0, 0, 0, 0);
        assertThat(newPrice, equalTo(0d));
    }
}