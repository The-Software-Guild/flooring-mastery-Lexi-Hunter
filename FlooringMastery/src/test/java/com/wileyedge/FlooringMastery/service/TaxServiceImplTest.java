package com.wileyedge.FlooringMastery.service;

import com.wileyedge.FlooringMastery.dao.TaxDao;
import com.wileyedge.FlooringMastery.dao.TaxDaoImpl;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("TaxServiceImpl Test")
public class TaxServiceImplTest {
    private TaxServiceImpl taxService;
    private TaxDao taxDao;

    @BeforeEach
    public void setup() {
        System.out.println("Inside setup of TaxServiceImplTest");
        taxDao = new TaxDaoImpl();
        taxService = new TaxServiceImpl(taxDao);
    }
    
    @Test
    @DisplayName("getTaxRate method test")
    public void testGetTaxRate() {
        System.out.println("Inside testGetTaxRate of TaxServiceImplTest");

        BigDecimal actualResult = taxService.getTaxRate("TX");
        BigDecimal expectedResult = new BigDecimal("4.45");
        int comparisonResult = actualResult.setScale(2, RoundingMode.HALF_UP).compareTo(expectedResult.setScale(2, RoundingMode.HALF_UP));
        assertEquals(0, comparisonResult, "The tax rate of Texas should be 4.45");
    }
    
    @Test
    @DisplayName("isValidState method test")
    public void testIsValidState() {
        System.out.println("Inside testIsValidState of TaxServiceImplTest");

        boolean actualResult = taxService.isValidState("TX");
        boolean expectedResult = true;
        assertEquals(expectedResult, actualResult, "TX should be considered a valid state");
        
        actualResult = taxService.isValidState("PY");
        expectedResult = false;
        assertEquals(expectedResult, actualResult, "PY should not be considered a valid state");
    }
    
    @AfterAll
    public void cleanup() {
        System.out.println("Inside cleanup of TaxServiceImplTest");
        taxService = null;
        taxDao = null;
    }
}
