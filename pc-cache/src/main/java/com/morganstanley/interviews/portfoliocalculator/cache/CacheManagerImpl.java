package com.morganstanley.interviews.portfoliocalculator.cache;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManagerImpl implements CacheManager {
    private final Map<CacheName, Cache> cacheMap = new ConcurrentHashMap<>();

    @Override
    @PostConstruct
    public void init() {
        for (final CacheName cacheName : CacheName.values()) {
            addCache(cacheName, new CacheImpl());
        }
    }

    @Override
    public Cache getCache(final CacheName cacheName) {
        return this.cacheMap.get(cacheName);
    }

    @Override
    public void addCache(final CacheName cacheName, final Cache cache) {
        this.cacheMap.put(cacheName, cache);
    }
}
