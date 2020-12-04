package com.morganstanley.interviews.portfoliocalculator.cache;

import com.morganstanley.interviews.portfoliocalculator.model.Instrument;
import com.morganstanley.interviews.portfoliocalculator.model.InstrumentImpl;
import com.morganstanley.interviews.portfoliocalculator.model.InstrumentType;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class CacheImplTest {
    private Cache<Instrument> instrumentCache;

    @Before
    public void setUp() {
        this.instrumentCache = new CacheImpl<>();
    }

    @Test
    public void shouldFindEntryByPrimaryKey() {
        final Instrument instrument1 = new InstrumentImpl("CS001", InstrumentType.COMMON_STOCK);
        this.instrumentCache.addRecord(instrument1);
        final Instrument instrument2 = new InstrumentImpl("CS002", InstrumentType.COMMON_STOCK);
        this.instrumentCache.addRecord(instrument2);

        assertThat(this.instrumentCache.getRecord("CS001"), CoreMatchers.equalTo(instrument1));
        assertThat(this.instrumentCache.getRecord("CS002"), CoreMatchers.equalTo(instrument2));
    }
}