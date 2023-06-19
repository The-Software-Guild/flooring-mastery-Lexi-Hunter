package com.wileyedge.FlooringMastery.dao;

import java.math.BigDecimal;
import java.util.List;
import com.wileyedge.FlooringMastery.model.Tax;

public interface TaxDao {
    List<Tax> getTaxRates();
    BigDecimal getTaxRate(String state);
    void readTaxFromFile();
    boolean isValidState(String state);
}
