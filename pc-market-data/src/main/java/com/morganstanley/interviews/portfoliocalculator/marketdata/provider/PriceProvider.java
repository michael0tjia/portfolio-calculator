package com.morganstanley.interviews.portfoliocalculator.marketdata.provider;

import com.morganstanley.interviews.portfoliocalculator.model.CommonStock;

public interface PriceProvider extends Runnable {
    CommonStock getCommonStock();
}
