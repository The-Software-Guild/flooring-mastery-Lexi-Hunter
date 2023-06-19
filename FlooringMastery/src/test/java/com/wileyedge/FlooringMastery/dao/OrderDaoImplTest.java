package com.wileyedge.FlooringMastery.dao;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("OrderDaoImpl Test")
public class OrderDaoImplTest {
    private OrderDaoImpl orderDao;

    @BeforeEach
    public void setup() {
        System.out.println("Inside setup of OrderDaoImplTest");
        orderDao = new OrderDaoImpl();
        orderDao.readOrdersFromFile();
    }

    @Test
    @DisplayName("getAllOrders method test")
    public void testGetAllOrders() {
        System.out.println("Inside testGetAllOrders of OrderDaoImplTest");

        int actualResult = orderDao.getAllOrders().size();
        int expectedResult = 3;
        assertEquals(expectedResult, actualResult, "The total number of orders should be 3.");
    }
    
    @Test
    @DisplayName("getOrdersByDate method test")
    public void testGetOrdersByDate() {
        System.out.println("Inside testGetOrdersByDate of OrderDaoImplTest");

        LocalDate date = LocalDate.of(2013, 6, 2);
        int actualResult = orderDao.getOrdersByDate(date).size();
        int expectedResult = 2;
        assertEquals(expectedResult, actualResult, "The number of orders on " + date + " should be 2.");
    }
    
    @Test
    @DisplayName("getOrderByOrderNumber method test")
    public void testGetOrderByOrderNumber() {
        System.out.println("Inside testGetOrderByOrderNumber of OrderDaoImplTest");

        int orderNumber = 1;
        com.wileyedge.FlooringMastery.model.Order actualResult = orderDao.getOrderByOrderNumber(orderNumber);
        assertNotNull(actualResult, "An order with order number " + orderNumber + " should exist.");
        
        orderNumber = 2;
        actualResult = orderDao.getOrderByOrderNumber(orderNumber);
        assertNotNull(actualResult, "An order with order number " + orderNumber + " should exist.");
        
        orderNumber = 3;
        actualResult = orderDao.getOrderByOrderNumber(orderNumber);
        assertNotNull(actualResult, "An order with order number " + orderNumber + " should exist.");
    }
    
    @Test
    public void testAddOrder() {
    	com.wileyedge.FlooringMastery.model.Order newOrder = new com.wileyedge.FlooringMastery.model.Order();
        newOrder.setOrderNumber(10);
        newOrder.setCustomerName("Test Customer");
        newOrder.setState("Test State");
        newOrder.setTaxRate(new BigDecimal("5.75"));
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("200.00"));
        newOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        newOrder.setLabourCostPerSquareFoot(new BigDecimal("4.75"));
        newOrder.setMaterialCost(new BigDecimal("1030.00"));
        newOrder.setLabourCost(new BigDecimal("950.00"));
        newOrder.setTax(new BigDecimal("113.50"));
        newOrder.setTotal(new BigDecimal("2093.50"));
        newOrder.setDate(LocalDate.now());
        
        orderDao.addOrder(newOrder);

        com.wileyedge.FlooringMastery.model.Order retrievedOrder = orderDao.getOrderByOrderNumber(10);
        System.out.println(retrievedOrder);
        assertTrue(retrievedOrder.equals(newOrder), "Retrieved order should be the same as the one added.");
    }
    
    @Test
    public void testRemoveOrder() {
    	
    	// Get existing order
    	com.wileyedge.FlooringMastery.model.Order orderToRemove = orderDao.getOrderByOrderNumber(1);
    	
        // Now remove the order
        orderDao.removeOrder(orderToRemove);

        // Check the order is removed correctly by attempting to get it by its ID
        com.wileyedge.FlooringMastery.model.Order removedOrder = orderDao.getOrderByOrderNumber(1);
        assertNull(removedOrder);
    }


    @AfterAll
    public void cleanup() {
        System.out.println("Inside cleanup of OrderDaoImplTest");
        orderDao = null;
    }
}
