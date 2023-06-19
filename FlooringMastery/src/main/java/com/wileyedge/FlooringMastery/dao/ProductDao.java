package com.wileyedge.FlooringMastery.dao;

import java.math.BigDecimal;
import java.util.List;
import com.wileyedge.FlooringMastery.model.Product;

public interface ProductDao {
    List<Product> getProducts();
    BigDecimal getCostPerSquareFoot(String productType);
    BigDecimal getlabourCostPerSquareFoot(String productType);
    void readProductFromFile();
}
