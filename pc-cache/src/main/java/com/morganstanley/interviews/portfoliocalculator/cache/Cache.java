package com.morganstanley.interviews.portfoliocalculator.cache;

import com.morganstanley.interviews.portfoliocalculator.model.Cacheable;

import java.util.List;

public interface Cache<T extends Cacheable> {
    void addRecord(T record);

    void addRecords(final List<T> records);

    void deleteRecord(T record);

    T getRecord(Object primaryKey);

    List<T> getAllRecords();
}
