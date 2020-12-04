package com.morganstanley.interviews.portfoliocalculator.dataloader;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class DataLoaderManagerImpl implements DataLoaderManager {
    private final List<DataLoader> dataLoaders;

    public DataLoaderManagerImpl(final List<DataLoader> dataLoaders) {
        this.dataLoaders = dataLoaders;
    }

    @PostConstruct
    public void init() {
        doAllInitialLoads();
    }

    @Override
    public void doAllInitialLoads() {
        for (final DataLoader dataLoader : this.dataLoaders) {
            try {
                dataLoader.doInitialLoad();
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
