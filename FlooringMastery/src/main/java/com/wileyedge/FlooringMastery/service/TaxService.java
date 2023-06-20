package com.wileyedge.FlooringMastery.service;

import java.math.BigDecimal;
import java.util.List;
import com.wileyedge.FlooringMastery.model.Tax;

public interface TaxService {
    void readTaxFromFile();
    BigDecimal getTaxRate(String state);
    boolean isValidState(String state);
}
