package com.wileyedge.FlooringMastery.service;

import com.wileyedge.FlooringMastery.dao.ProductDao;
import com.wileyedge.FlooringMastery.dao.ProductDaoImpl;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("ProductServiceImpl Test")
public class ProductServiceImplTest {
    private ProductServiceImpl productService;
    private ProductDao productDao;

    @BeforeEach
    public void setup() {
        System.out.println("Inside setup of ProductServiceImplTest");
        productDao = new ProductDaoImpl();
        productService = new ProductServiceImpl(productDao);
    }

    @Test
    @DisplayName("getProducts method test")
    public void testGetProducts() {
        System.out.println("Inside testGetProducts of ProductServiceImplTest");

        int actualResult = productService.getProducts().size();
        int expectedResult = 4;
        assertEquals(expectedResult, actualResult, "The total number of products should be 4.");
    }
    
    @Test
    @DisplayName("getCostPerSquareFoot method test")
    public void testGetCostPerSquareFoot() {
        System.out.println("Inside testGetCostPerSquareFoot of ProductServiceImplTest");

        BigDecimal actualResult = productService.getCostPerSquareFoot("Carpet");
        BigDecimal expectedResult = new BigDecimal("2.25");
        int comparisonResult = actualResult.setScale(2, RoundingMode.HALF_UP).compareTo(expectedResult.setScale(2, RoundingMode.HALF_UP));
        assertEquals(0, comparisonResult, "The cost per square foot of Carpet should be 2.25");
    }
    
    @Test
    @DisplayName("getlabourCostPerSquareFoot method test")
    public void testGetlabourCostPerSquareFoot() {
        System.out.println("Inside testGetlabourCostPerSquareFoot of ProductServiceImplTest");

        BigDecimal actualResult = productService.getLabourCostPerSquareFoot("Carpet");
        BigDecimal expectedResult = new BigDecimal("2.10");
        int comparisonResult = actualResult.setScale(2, RoundingMode.HALF_UP).compareTo(expectedResult.setScale(2, RoundingMode.HALF_UP));
        assertEquals(0, comparisonResult , "The labour cost per square foot of Carpet should be 2.10");
    }
    
    @AfterAll
    public void cleanup() {
        System.out.println("Inside cleanup of ProductServiceImplTest");
        productService = null;
        productDao = null;
    }
}