package com.morganstanley.interviews.portfoliocalculator.cache;

import com.morganstanley.interviews.portfoliocalculator.model.Cacheable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheImpl<T extends Cacheable> implements Cache<T> {
    Map<Object, T> map = new ConcurrentHashMap<>();

    @Override
    public void addRecord(final T record) {
        this.map.put(record.getPrimaryKey(), record);
    }

    @Override
    public void addRecords(final List<T> records) {
        for (final T record : records) {
            this.map.put(record.getPrimaryKey(), record);
        }
    }

    @Override
    public void deleteRecord(final T record) {
        this.map.remove(record.getPrimaryKey());
    }

    @Override
    public T getRecord(final Object primaryKey) {
        return this.map.get(primaryKey);
    }

    @Override
    public List<T> getAllRecords() {
        final List<T> allRecords = new ArrayList<>(this.map.size());
        for (final Map.Entry<Object, T> entry : this.map.entrySet()) {
            allRecords.add(entry.getValue());
        }
        return allRecords;
    }
}
