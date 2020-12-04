package com.morganstanley.interviews.portfoliocalculator.dataloader;

import com.morganstanley.interviews.portfoliocalculator.cache.Cache;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheManager;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheName;
import com.morganstanley.interviews.portfoliocalculator.model.Position;
import com.morganstanley.interviews.portfoliocalculator.model.PositionImpl;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class PositionLoader implements DataLoader {
    private static final String CVS_SPLIT_BY = ",";
    private final Cache<Position> positionCache;
    private final InputStream inputStream;

    public PositionLoader(final CacheManager cacheManager, final InputStream inputStream) {
        this.positionCache = cacheManager.getCache(CacheName.Position);
        this.inputStream = inputStream;
    }

    @Override
    public void doInitialLoad() throws FileNotFoundException {
        String line;

        final Scanner scanner = new Scanner(this.inputStream);
        final String lineSeparator = System.getProperty("line.separator");

        try {
            while (scanner.hasNextLine()) {
                final String[] values = scanner.nextLine().split(CVS_SPLIT_BY);
                final String ticker = values[0];
                final long quantity = Long.valueOf(values[1]);
                final Position position = new PositionImpl(ticker);
                position.setQuantity(quantity);
                this.positionCache.addRecord(position);
            }
        } finally {
            scanner.close();
        }
    }
}
