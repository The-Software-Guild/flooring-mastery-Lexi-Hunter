package com.wileyedge.FlooringMastery.service;

import com.wileyedge.FlooringMastery.dao.OrderDao;
import com.wileyedge.FlooringMastery.dao.OrderDaoImpl;
import com.wileyedge.FlooringMastery.model.Order;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("OrderServiceImpl Test")
public class OrderServiceImplTest {
    private OrderServiceImpl orderService;
    private OrderDao orderDao;

    @BeforeEach
    public void setup() {
        System.out.println("Inside setup of OrderServiceImplTest");
        orderDao = new OrderDaoImpl();
        orderService = new OrderServiceImpl(orderDao);
    }

    @Test
    @DisplayName("getOrdersByDate method test")
    public void testGetOrdersByDate() {
        System.out.println("Inside testGetOrdersByDate of OrderServiceImplTest");

        LocalDate date = LocalDate.of(2013, 6, 2);
        List<Order> orders = orderService.getOrdersByDate(date);
        int actualResult = orders.size();
        int expectedResult = 2;
        assertEquals(expectedResult, actualResult, "The number of orders on " + date + " should be 2.");
    }

    @Test
    @DisplayName("getAllOrders method test")
    public void testGetAllOrders() {
        System.out.println("Inside testGetAllOrders of OrderServiceImplTest");

        List<Order> orders = orderService.getAllOrders();
        
        for(Order o : orders) {
        	System.out.println(o);
        }

        int actualResult = orders.size();
        int expectedResult = 3;
        assertEquals(expectedResult, actualResult, "The total number of orders should be 3.");
    }

    @Test
    @DisplayName("getOrderByOrderNumber method test")
    public void testGetOrderByOrderNumber() {
        System.out.println("Inside testGetOrderByOrderNumber of OrderServiceImplTest");

        int orderNumber = 1;
        Order actualResult = orderService.getOrderByOrderNumber(orderNumber);
        assertNotNull(actualResult, "An order with order number " + orderNumber + " should exist.");
        
        orderNumber = 2;
        actualResult = orderService.getOrderByOrderNumber(orderNumber);
        assertNotNull(actualResult, "An order with order number " + orderNumber + " should exist.");
        
        orderNumber = 3;
        actualResult = orderService.getOrderByOrderNumber(orderNumber);
        assertNotNull(actualResult, "An order with order number " + orderNumber + " should exist.");
    }

    @Test
    @DisplayName("addOrder method test")
    public void testAddOrder() {
        System.out.println("Inside testAddOrder of OrderServiceImplTest");

        Order newOrder = new Order();
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

        orderService.addOrder(newOrder);


        Order retrievedOrder = orderService.getOrderByOrderNumber(10);
        assertEquals(retrievedOrder, newOrder, "Retrieved order should be the same as the one added.");
    }

    @Test
    @DisplayName("Update Order method test")
    public void testUpdateOrder() {
        System.out.println("Inside testUpdateOrder of OrderServiceImplTest");

        Order existingOrder = orderService.getOrderByOrderNumber(1);
        
        Order updatedOrder = new Order();
        
        updatedOrder.setOrderNumber(existingOrder.getOrderNumber());
        updatedOrder.setCustomerName("New Customer Name");
        updatedOrder.setState(existingOrder.getState());
        updatedOrder.setTaxRate(existingOrder.getTaxRate());
        updatedOrder.setProductType(existingOrder.getProductType());
        updatedOrder.setArea(existingOrder.getArea());
        updatedOrder.setCostPerSquareFoot(existingOrder.getCostPerSquareFoot());
        updatedOrder.setLabourCostPerSquareFoot(existingOrder.getLabourCostPerSquareFoot());
        updatedOrder.setMaterialCost(existingOrder.getMaterialCost());
        updatedOrder.setLabourCost(existingOrder.getLabourCost());
        updatedOrder.setTax(existingOrder.getTax());
        updatedOrder.setTotal(existingOrder.getTotal());
        updatedOrder.setDate(existingOrder.getDate());
        
        existingOrder.setCustomerName("Updated Customer");
        orderService.updateOrder(existingOrder, updatedOrder);

        Order retrievedOrder = orderService.getOrderByOrderNumber(1);
        assertEquals(retrievedOrder.getCustomerName(), "New Customer Name", "Order should have been updated.");
    }

    @Test
    @DisplayName("removeOrder method test")
    public void testRemoveOrder() {
        System.out.println("Inside testRemoveOrder of OrderServiceImplTest");

        Order orderToRemove = orderService.getOrderByOrderNumber(1);
        orderService.removeOrder(orderToRemove);

        Order removedOrder = orderService.getOrderByOrderNumber(1);
        assertNull(removedOrder, "Removed order should not exist in the service.");
    }
    
    @AfterAll
    public void cleanup() {
        System.out.println("Inside cleanup of OrderServiceImplTest");
        orderService = null;
        orderDao = null;
    }
}