package com.wileyedge.FlooringMastery.service;

import java.math.BigDecimal;
import java.util.List;
import com.wileyedge.FlooringMastery.model.Product;

public interface ProductService {
    void readProductFromFile();
    List<Product> getProducts();
    BigDecimal getCostPerSquareFoot(String productType);
    BigDecimal getLabourCostPerSquareFoot(String productType);
    boolean isValidProductType(String productType);
}
