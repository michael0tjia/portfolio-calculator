package com.morganstanley.interviews.portfoliocalculator.marketdata.math;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RandomUtilTest {
    @Mock
    Random random;

    @Test
    public void testRandomDouble() throws Exception {
        double randomDouble;

        when(this.random.nextDouble()).thenReturn(0.5d);
        randomDouble = RandomUtil.getRandomDouble(this.random, 1.0d, 9.0d);
        assertThat(randomDouble, CoreMatchers.equalTo(5.0d));

        when(this.random.nextDouble()).thenReturn(0.0d);
        randomDouble = RandomUtil.getRandomDouble(this.random, 1.0d, 9.0d);
        assertThat(randomDouble, CoreMatchers.equalTo(1.0d));

        when(this.random.nextDouble()).thenReturn(1.0d);
        randomDouble = RandomUtil.getRandomDouble(this.random, 1.0d, 9.0d);
        assertThat(randomDouble, CoreMatchers.equalTo(9.0d));
    }
}