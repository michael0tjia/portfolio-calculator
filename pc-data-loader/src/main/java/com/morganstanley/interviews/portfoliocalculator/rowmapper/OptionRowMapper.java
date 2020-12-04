package com.morganstanley.interviews.portfoliocalculator.rowmapper;

import com.morganstanley.interviews.portfoliocalculator.model.Option;
import com.morganstanley.interviews.portfoliocalculator.model.OptionImpl;
import com.morganstanley.interviews.portfoliocalculator.model.OptionRight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OptionRowMapper implements RowMapper<Option> {
    private static final Logger logger = LoggerFactory.getLogger(OptionRowMapper.class);

    private static final String COL_TICKER = "ticker";
    private static final String COL_UNDERLYING_TICKER = "underlying_ticker";
    private static final String COL_RIGHT = "right";
    private static final String COL_TIME_TO_MATURITY = "time_to_maturity";
    private static final String COL_STRIKE_PRICE = "strike_price";

    @Override
    public Option mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        try {
            final String ticker = rs.getString(COL_TICKER);
            final String underlygingTicker = rs.getString(COL_UNDERLYING_TICKER);
            final OptionRight optionRight = OptionRight.valueOf(rs.getString(COL_RIGHT));
            final double timeToMaturity = rs.getDouble(COL_TIME_TO_MATURITY);
            final double strikePrice = rs.getDouble(COL_STRIKE_PRICE);

            final Option option = new OptionImpl(ticker, underlygingTicker, optionRight);
            option.setTimeToMaturity(timeToMaturity);
            option.setStrikePrice(strikePrice);
            return option;
        } catch (final Exception e) {
            logger.warn("Exception in database row mapping to object", e);
        }
        return null;
    }
}
