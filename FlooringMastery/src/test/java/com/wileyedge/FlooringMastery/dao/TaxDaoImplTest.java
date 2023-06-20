package com.wileyedge.FlooringMastery.dao;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("TaxDaoImpl Test")
public class TaxDaoImplTest {
    private TaxDaoImpl taxDao;

    @BeforeEach
    public void setup() {
        System.out.println("Inside setup of TaxDaoImplTest");
        taxDao = new TaxDaoImpl();
        taxDao.readTaxFromFile();
    }
     
    @Test
    @DisplayName("getTaxRate method test")
    public void testGetTaxRate() {
        System.out.println("Inside testGetTaxRate of taxDaoImplTest");

        BigDecimal actualResult = taxDao.getTaxRate("TX");
        BigDecimal expectedResult = new BigDecimal("4.45");
        int comparisonResult = actualResult.setScale(2, RoundingMode.HALF_UP).compareTo(expectedResult.setScale(2, RoundingMode.HALF_UP));
        assertEquals(0, comparisonResult, "The tax rate of Texas should be 4.45");
    }
    
    @Test
    @DisplayName("isValidState method test")
    public void testIsValidState() {
        System.out.println("Inside testIsValidState of taxDaoImplTest");

        boolean actualResult = taxDao.isValidState("TX");
        boolean expectedResult = true;

        assertEquals(actualResult, expectedResult, "TX should be considered a valid state");
        
        actualResult = taxDao.isValidState("PY");
        expectedResult = false;

        assertEquals(actualResult, expectedResult, "PY should not be considered a valid state");
    }
    
    @AfterAll
    public void cleanup() {
        System.out.println("Inside cleanup of taxDaoImplTest");
        taxDao = null;
    }
}
