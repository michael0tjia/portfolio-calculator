package com.morganstanley.interviews.portfoliocalculator.cache;

import com.morganstanley.interviews.portfoliocalculator.model.Instrument;
import com.morganstanley.interviews.portfoliocalculator.model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

public class CacheManagerImplTest {
    private CacheManagerImpl cacheManagerImpl;

    @Before
    public void setUp() {
        this.cacheManagerImpl = new CacheManagerImpl();
    }

    @Test
    public void shouldFindCacheByCacheName() {
        final Cache<Instrument> instrumentCache = new CacheImpl<>();
        final Cache<Position> positionCache = new CacheImpl<>();

        this.cacheManagerImpl.addCache(CacheName.Instrument, instrumentCache);
        this.cacheManagerImpl.addCache(CacheName.Position, positionCache);

        assertThat(this.cacheManagerImpl.getCache(CacheName.Instrument), sameInstance(instrumentCache));
        assertThat(this.cacheManagerImpl.getCache(CacheName.Position), sameInstance(positionCache));
    }
}