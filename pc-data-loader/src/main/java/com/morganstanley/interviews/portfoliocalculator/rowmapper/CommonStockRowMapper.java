package com.morganstanley.interviews.portfoliocalculator.rowmapper;

import com.morganstanley.interviews.portfoliocalculator.model.CommonStock;
import com.morganstanley.interviews.portfoliocalculator.model.CommonStockImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonStockRowMapper implements RowMapper<CommonStock> {
    private static final Logger logger = LoggerFactory.getLogger(CommonStockRowMapper.class);

    private static final String COL_TICKER = "ticker";
    private static final String COL_PRICE = "price";
    private static final String COL_EXPECTED_RETURN = "expected_return";
    private static final String COL_VOLATILITY = "volatility";
    
    @Override
    public CommonStock mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        try {
            final String ticker = rs.getString(COL_TICKER);
            final double price = rs.getDouble(COL_PRICE);
            final double expectedReturn = rs.getDouble(COL_EXPECTED_RETURN);
            final double volatility = rs.getDouble(COL_VOLATILITY);

            final CommonStock commonStock = new CommonStockImpl(ticker);
            commonStock.setPrice(price);
            commonStock.setExpectedReturn(expectedReturn);
            commonStock.setVolatility(volatility);
            return commonStock;
        } catch (final Exception e) {
            logger.warn("Exception in database row mapping to object", e);
        }
        return null;
    }
}
