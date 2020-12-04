package com.morganstanley.interviews.portfoliocalculator.dataloader;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

public class DatabaseSetup {
    private final JdbcTemplate jdbcTemplate;
    private final String createTablesSql;
    private final String insertTablesSql;

    public DatabaseSetup(final JdbcTemplate jdbcTemplate, final String createTablesSql, final String insertTablesSql) {
        this.jdbcTemplate = jdbcTemplate;
        this.createTablesSql = createTablesSql;
        this.insertTablesSql = insertTablesSql;
    }

    @PostConstruct
    public void init() {
        createTables();
        insertTables();
    }

    private void createTables() {
        this.jdbcTemplate.execute(this.createTablesSql);
    }

    private void insertTables() {
        this.jdbcTemplate.update(this.insertTablesSql);
    }
}
