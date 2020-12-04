package com.morganstanley.interviews.portfoliocalculator.core.service;

import com.morganstanley.interviews.portfoliocalculator.model.Option;

public interface OptionService {
    double getOptionPrice(Option option);
}
