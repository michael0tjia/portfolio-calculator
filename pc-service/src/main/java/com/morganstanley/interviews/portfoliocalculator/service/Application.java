package com.morganstanley.interviews.portfoliocalculator.service;

import com.morganstanley.interviews.portfoliocalculator.cache.CacheManager;
import com.morganstanley.interviews.portfoliocalculator.cache.CacheManagerImpl;
import com.morganstanley.interviews.portfoliocalculator.core.service.OptionService;
import com.morganstanley.interviews.portfoliocalculator.core.service.OptionServiceImpl;
import com.morganstanley.interviews.portfoliocalculator.core.service.PortfolioService;
import com.morganstanley.interviews.portfoliocalculator.core.service.PortfolioServiceImpl;
import com.morganstanley.interviews.portfoliocalculator.dataloader.*;
import com.morganstanley.interviews.portfoliocalculator.marketdata.MarketDataService;
import com.morganstanley.interviews.portfoliocalculator.marketdata.MockMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static String inputFilePath;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(final String[] args) {
        if (args.length > 0) {
            inputFilePath = args[0];
        }
        SpringApplication.run(Application.class, args);
    }

    private static String readInputStream(final InputStream inputStream) throws FileNotFoundException {
        final StringBuilder fileContents = new StringBuilder();
        final Scanner scanner = new Scanner(inputStream);
        final String lineSeparator = System.getProperty("line.separator");

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }

    @Bean
    public CacheManager cacheManager() {
        return new CacheManagerImpl();
    }

    @Bean
    public DatabaseSetup databaseSetup() throws IOException {
        this.resourceLoader.getResource("classpath:create-tables.sql").getInputStream();
        final String createTablesSql = readInputStream(this.resourceLoader.getResource("classpath:create-tables.sql").getInputStream());
        final String insertTablesSql = readInputStream(this.resourceLoader.getResource("classpath:insert-tables.sql").getInputStream());

        final DatabaseSetup databaseSetup = new DatabaseSetup(this.jdbcTemplate, createTablesSql, insertTablesSql);
        return databaseSetup;
    }

    @Bean
    public PositionLoader positionLoader() throws IOException {
        boolean inputFilesExists = false;
        if (inputFilePath != null) {
            final File inputFile = new File(inputFilePath);
            inputFilesExists = inputFile.exists();
        }
        final InputStream inputStream;
        if (inputFilesExists) {
            inputStream = new FileInputStream(inputFilePath);
        } else {
            inputStream = this.resourceLoader.getResource("classpath:sample-portfolio.csv").getInputStream();
        }
        final PositionLoader positionLoader = new PositionLoader(cacheManager(), inputStream);
        return positionLoader;
    }

    public InstrumentLoader instrumentLoader() {

        return new InstrumentLoader(cacheManager(), this.jdbcTemplate);
    }

    @Bean
    public DataLoaderManager dataLoaderManager() throws IOException {
        final List<DataLoader> dataLoaders = new ArrayList<>();
        dataLoaders.add(positionLoader());
        dataLoaders.add(instrumentLoader());
        return new DataLoaderManagerImpl(dataLoaders);
    }

    @Bean
    public OptionService optionService() {
        return new OptionServiceImpl(cacheManager());
    }

    @Bean
    public PortfolioService portfolioService() {
        return new PortfolioServiceImpl(cacheManager());
    }

    @Bean
    public MarketDataService marketDataService() {
        return new MockMarketDataService(cacheManager());
    }

    @Bean
    public PortfolioManager portfolioManager() {
        return new PortfolioManagerImpl(cacheManager(), marketDataService(), optionService(), portfolioService());
    }
}
