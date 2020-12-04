package com.morganstanley.interviews.portfoliocalculator.rowmapper;

import com.morganstanley.interviews.portfoliocalculator.model.Instrument;
import com.morganstanley.interviews.portfoliocalculator.model.InstrumentImpl;
import com.morganstanley.interviews.portfoliocalculator.model.InstrumentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstrumentRowMapper implements RowMapper<Instrument> {
    private static final Logger logger = LoggerFactory.getLogger(InstrumentRowMapper.class);

    private static final String COL_TICKER = "ticker";
    private static final String COL_INSTRUMENT_TYPE = "instrument_type";

    @Override
    public Instrument mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        try {
            final String ticker = rs.getString(COL_TICKER);
            final InstrumentType instrumentType = InstrumentType.valueOf(rs.getString(COL_INSTRUMENT_TYPE));

            final Instrument instrument = new InstrumentImpl(ticker, instrumentType);
            return instrument;
        } catch (final Exception e) {
            logger.warn("Exception in database row mapping to object", e);
        }
        return null;
    }
}
