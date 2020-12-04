package com.morganstanley.interviews.portfoliocalculator.dataloader;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface DataLoader {
    void doInitialLoad() throws FileNotFoundException, SQLException;
}
