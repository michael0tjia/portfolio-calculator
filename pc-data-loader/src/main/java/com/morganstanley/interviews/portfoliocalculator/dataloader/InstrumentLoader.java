package com.morganstanley.interviews.portfoliocalculator.dataloader;

import com.morganstanley.interviews.portfoliocalculator.cache.Cache;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheManager;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheName;
import com.morganstanley.interviews.portfoliocalculator.model.CommonStock;
import com.morganstanley.interviews.portfoliocalculator.model.Instrument;
import com.morganstanley.interviews.portfoliocalculator.model.Option;
import com.morganstanley.interviews.portfoliocalculator.rowmapper.CommonStockRowMapper;
import com.morganstanley.interviews.portfoliocalculator.rowmapper.InstrumentRowMapper;
import com.morganstanley.interviews.portfoliocalculator.rowmapper.OptionRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class InstrumentLoader implements DataLoader {
    private final static String INSTRUMENT_SQL = "select * from instrument";
    private final static String COMMON_STOCK_SQL = "select * from common_stock";
    private final static String OPTION_SQL = "select * from option";
    private final Cache<Instrument> instrumentCache;
    private final Cache<CommonStock> commonStockCache;
    private final Cache<Option> optionCache;
    private final JdbcTemplate jdbcTemplate;

    public InstrumentLoader(final CacheManager cacheManager, final JdbcTemplate jdbcTemplate) {
        this.instrumentCache = cacheManager.getCache(CacheName.Instrument);
        this.commonStockCache = cacheManager.getCache(CacheName.CommonStock);
        this.optionCache = cacheManager.getCache(CacheName.Option);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void doInitialLoad() {
        final List<Instrument> instruments = this.jdbcTemplate.query(INSTRUMENT_SQL, new InstrumentRowMapper());
        this.instrumentCache.addRecords(instruments);

        final List<CommonStock> commonStocks = this.jdbcTemplate.query(COMMON_STOCK_SQL, new CommonStockRowMapper());
        this.commonStockCache.addRecords(commonStocks);

        final List<Option> options = this.jdbcTemplate.query(OPTION_SQL, new OptionRowMapper());
        this.optionCache.addRecords(options);
    }
}
