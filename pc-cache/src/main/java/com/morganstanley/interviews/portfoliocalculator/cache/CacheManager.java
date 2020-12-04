package com.morganstanley.interviews.portfoliocalculator.cache;

public interface CacheManager {
    void init();

    Cache getCache(CacheName cacheName);

    void addCache(final CacheName cacheName, final Cache cache);
}
